package com.novax.ex.demo.infrastructure.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.novax.ex.common.base.BaseMapper;
import com.novax.ex.demo.infrastructure.entity.DemoEntity;

/**
 * Description:
 *
 * @author shaw
 * @date 6/24/22 14:52
 */
public interface DemoMapper extends BaseMapper<DemoEntity> {

    @DS("slave")
    DemoEntity selectByPrimaryKey(Long id);

    int update(DemoEntity entity);

    int deleteByPrimaryKey(Long id);
}
