package com.novax.ex.demo1.infrastructure.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.novax.ex.demo1.infrastructure.entity.DemoEntity;
import org.apache.ibatis.annotations.Param;

/**
 * Description:
 *
 * @author shaw
 * @date 6/24/22 14:52
 */
public interface Demo1Mapper {

    @DS("slave")
    DemoEntity selectByPrimaryKey(Long id);

    int update(DemoEntity entity);

    int deleteByPrimaryKey(Long id);

    int insert(DemoEntity entity);

    int incr(@Param("id") Long id, @Param("num") Integer num);
}
