package com.novax.ex.common.base;

import com.novax.ex.common.util.CopyUtils;
import com.novax.ex.common.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * Description: 项目基础ServiceImpl
 *
 * @author shaoqiping
 * @date 6/30/21 3:59 PM
 */
@Slf4j
public abstract class BaseService {

    /**
     * 关联mapper
     * @return
     */
    protected abstract BaseMapper getBaseMapper();

    // 根据主键查询
    public <T extends BaseEntity> T find(Long id) {
        return getBaseMapper().selectByPrimaryKey(id);
    }

    // 列表查询
    public <T extends BaseEntity> List<T> findList(Map<String, Object> param) {
        return getBaseMapper().selectList(param);
    }

    // 新增
    @Transactional(rollbackFor = RuntimeException.class)
    public <T extends BaseEntity> boolean add(T t) {
        return getBaseMapper().insert(t) > 0;
    }

    // 动态新增
    @Transactional(rollbackFor = RuntimeException.class)
    public <T extends BaseEntity> boolean addSelective(T t) {
        return getBaseMapper().insertSelective(t) > 0;
    }

    // 修改
    @Transactional(rollbackFor = RuntimeException.class)
    public <T extends BaseEntity> boolean modify(T t) {
        return getBaseMapper().updateByPrimaryKey(t) > 0;
    }

    // 动态修改
    @Transactional(rollbackFor = RuntimeException.class)
    public <T extends BaseEntity> boolean modifySelective(T t) {
        return getBaseMapper().updateByPrimaryKeySelective(t) > 0;
    }

    // 根据主键删除
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean remove(Long id) {
        return getBaseMapper().deleteByPrimaryKey(id) > 0;
    }

    // 条件分页查询
    public <T> void findPage(PageUtil<T> pageInfo, Class<T> clazz) {
        long total = getBaseMapper().count(pageInfo);
        pageInfo.setTotal(total);
        if (total > 0) {
            pageInfo.setItems(CopyUtils.copyList(getBaseMapper().selectPage(pageInfo), clazz));
        } else {
            pageInfo.setItems(new ArrayList<>());
        }
    }

    // 条件分页查询
    public void findPage(PageUtil pageInfo) {
        long total = getBaseMapper().count(pageInfo);
        pageInfo.setTotal(total);
        if (total > 0) {
            pageInfo.setItems(getBaseMapper().selectPage(pageInfo));
        } else {
            pageInfo.setItems(new ArrayList<>());
        }
    }
}
