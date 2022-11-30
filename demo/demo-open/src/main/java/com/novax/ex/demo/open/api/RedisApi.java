package com.novax.ex.demo.open.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description: redis使用案例
 *
 * @author my.miao
 * @date 2022/11/30 17:06
 */
@Tag(name = "redis-demo", description = "redis-demo")
@RequestMapping
public interface RedisApi {
}
