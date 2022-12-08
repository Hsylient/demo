package com.novax.ex.demo.provider.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.novax.ex.common.core.redis.RedisUtil;
import com.novax.ex.common.results.ReturnResult;
import com.novax.ex.demo.provider.common.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
@Order(1)
public class RecommitAspect {

    @Pointcut("@annotation(com.novax.ex.demo.provider.common.interceptor.Recommit)")
    public void recommitPointcut() {
    }

    @Around("recommitPointcut()")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        boolean recommited = false;
        Object result;
        String cacheKey = null;
        boolean isDel = false;
        try {
            MethodSignature signature = (MethodSignature) jp.getSignature();
            Method m = signature.getMethod();
            Recommit rr = m.getAnnotation(Recommit.class);
            String ps = rr.ps();
            isDel = rr.del();
            Object[] args = jp.getArgs();
            StringBuilder sb = new StringBuilder();
            if (args == null || args.length == 0) {
                sb.append("NOARGS");
            } else if (StringUtils.isBlank(ps)) {
                Arrays.stream(args).forEach(a -> sb.append(JSON.toJSONString(a)).append("-"));
            } else {
                String[] ns = ps.split(",");
                if (ns.length > args.length) {
                    throw new Exception("ps param count invalid!");
                }
                for (String p : ns) {
                    if (!NumberUtils.isDigits(p) || Integer.parseInt(p) > args.length) {
                        throw new Exception("ps param value invalid!");
                    }
                    int idx = Integer.parseInt(p);
                    sb.append(JSON.toJSONString(args[idx - 1])).append("-");
                }
            }
            int ttl = rr.ttl();
            String key = sb.toString();
            String md5 = DigestUtils.md5Hex(key);
            //特殊改造，根据服务名称来生成，考虑到nacos微服务部署时实例名称一样，所以取名称当做一个组使用
            String appName = SpringUtils.getProperty("spring.application.name");
            cacheKey = "RR-" + appName + "-" + m.getDeclaringClass().getName() + "." + m.getName() + "-" + md5;
            //boolean exist = redisUtil.hasKey(cacheKey);
            recommited = !RedisUtil.setIfAbsent(cacheKey, "", ttl);
            //log.info("redis key:"+cacheKey);
        } catch (Exception e) {
            log.error("RecommitAspect.doAround error:", e);
        } finally {
            if (!recommited) {
                try {
                    result = jp.proceed();
                } finally {
                    if (isDel) {
                        try {
                            RedisUtil.delete(cacheKey);
                        } catch (Exception ignored) {
                        }
                    }
                }
            } else {
                result = ReturnResult.fail("重复提交");
                //methodLogAspect.doAfter(jp,result);
            }
        }
        return result;
    }

}

