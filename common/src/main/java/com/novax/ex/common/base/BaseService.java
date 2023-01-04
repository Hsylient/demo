package com.novax.ex.common.base;

import com.novax.ex.common.util.CopyUtils;
import com.novax.ex.common.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Description: 项目基础ServiceImpl
 *
 * @author shaoqiping
 * @date 6/30/21 3:59 PM
 */
@Slf4j
public abstract class BaseService<M extends BaseMapper<T>, T extends BaseEntity> {
    @Autowired
    protected M mapper;

    // 根据主键查询
    public T find(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    // 列表查询
    public List<T> findList(Map<String, Object> param) {
        return mapper.selectList(param);
    }

    // 新增
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean add(T t) {
        return mapper.insert(t) > 0;
    }

    // 动态新增
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean addSelective(T t) {
        return mapper.insertSelective(t) > 0;
    }

    // 修改
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean modify(T t) {
        return mapper.updateByPrimaryKey(t) > 0;
    }

    // 动态修改
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean modifySelective(T t) {
        return mapper.updateByPrimaryKeySelective(t) > 0;
    }

    // 根据主键删除
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean remove(Long id) {
        return mapper.deleteByPrimaryKey(id) > 0;
    }

    // 条件分页查询
    public void findPage(PageUtil<T> pageInfo, Class<T> clazz) {
        long total = mapper.count(pageInfo);
        pageInfo.setTotal(total);
        if (total > 0) {
            pageInfo.setItems(CopyUtils.copyList(mapper.selectPage(pageInfo), clazz));
        } else {
            pageInfo.setItems(Collections.emptyList());
        }
    }

    // 条件分页查询
    public void findPage(PageUtil<T> pageInfo) {
        long total = mapper.count(pageInfo);
        pageInfo.setTotal(total);
        if (total > 0) {
            pageInfo.setItems(mapper.selectPage(pageInfo));
        } else {
            pageInfo.setItems(Collections.emptyList());
        }
    }
}
