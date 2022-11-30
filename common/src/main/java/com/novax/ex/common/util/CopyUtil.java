package com.novax.ex.common.util;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 拷贝对象，list工具类
 * @Author Wade
 * @Date 10/27/22 3:27 PM
 * @Version 1.0
 */
public class CopyUtil {
    /**
     * 定义日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(CopyUtil.class);

    private static final ConcurrentHashMap<String, BeanCopier> CACHE_COPIER_MAP = new ConcurrentHashMap<>();

    /**
     * 复制对象属性
     *
     * @param srcObj    源对象
     * @param targetObj 目标对象
     * @param converter converter转换器
     */
    public static <T> T copyObject(Object srcObj, T targetObj, Converter converter) {
        if (null == srcObj) {
            logger.info("复制对象时源对象为null!");
            return null;
        }

        if (null == targetObj) {
            logger.info("复制对象时目标对象为null!");
            return null;
        }
        BeanCopier bc = getBeanCopierInstance(srcObj.getClass(), targetObj.getClass(), converter);
        bc.copy(srcObj, targetObj, converter);
        return targetObj;
    }

    /**
     * 复制对象属性
     *
     * @param srcObj    源对象
     * @param targetObj 目标对象
     */
    public static <T> T copyObject(Object srcObj, T targetObj) {
        return copyObject(srcObj, targetObj, null);
    }

    /**
     * 复制对象属性
     *
     * @param srcObj    源对象
     * @param targetObj 目标对象
     */
    public static <T> T copyObject(Object srcObj, Class<T> targetObj) {
        try {
            T t = targetObj.newInstance();
            copyObject(srcObj, t, null);
            return t;
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Cannot instantiate an object of %s.", targetObj));
        }
    }

    /**
     * 复制列表中所有元素到新列表中.
     *
     * @param srcList   源列表
     * @param converter 元素转换器
     * @param <S>       源列表元素类型
     * @param <T>       目标列表元素类型
     * @return 目标列表
     */
    public static <S, T> List<T> copyList(List<S> srcList, com.novax.ex.common.util.Converter<S, T> converter) {
        List<T> targetList = new ArrayList<T>();
        if (Objects.isNull(srcList) || srcList.isEmpty()) {
            return targetList;
        }
        if (converter == null) {
            throw new IllegalArgumentException("Converter cannot be null!");
        }
        for (S s : srcList) {
            targetList.add(converter.convert(s));
        }
        return targetList;
    }

    /**
     * 复制列表中所有元素到新列表中.
     *
     * @param srcList            源列表
     * @param targetElementClass 目标列表元素class
     * @param <S>                源列表元素类型
     * @param <T>                目标列表元素类型
     * @return 目标列表
     */
    public static <S, T> List<T> copyList(List<S> srcList, Class<T> targetElementClass) {
        List<T> targetList = new ArrayList<T>();
        if (Objects.isNull(srcList) || srcList.isEmpty()) {
            return targetList;
        }
        for (S src : srcList) {
            try {
                T target = targetElementClass.newInstance();
                CopyUtil.copyObject(src, target);
                targetList.add(target);
            } catch (Exception e) {
                throw new IllegalArgumentException(String.format("Cannot instantiate an object of %s.", targetElementClass));
            }
        }
        return targetList;
    }

    private static <S, T> BeanCopier getBeanCopierInstance(Class<S> sourceClass, Class<T> targetClass, Converter converter) {
        return CACHE_COPIER_MAP.computeIfAbsent(sourceClass.getName() + "#" + targetClass.getName(), sss ->
                BeanCopier.create(sourceClass, targetClass, converter != null)
        );
    }
}
