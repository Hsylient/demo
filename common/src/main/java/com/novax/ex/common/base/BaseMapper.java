package com.novax.ex.common.base;

import com.novax.ex.common.util.PageUtil;

import java.util.List;
import java.util.Map;

/**
 *
 * Description: BaseMapper
 *
 * @author shaoqiping
 * @date 6/16/21 1:46 PM
 */
public interface BaseMapper <T extends BaseEntity>{
    /**
     *
     * Description: 新增
     *
     * @param t t
     * @return int
     * @author shaoqiping
     * @date 6/16/21 1:47 PM
     */
    int insert(T t);

    /**
     *
     * Description: 修改
     *
     * @param t t
     * @return int
     * @author shaoqiping
     * @date 6/16/21 1:47 PM
     */
    int updateByPrimaryKey(T t);

    /**
     *
     * Description: 根据主键删除
     *
     * @param id id
     * @return int
     * @author shaoqiping
     * @date 6/16/21 1:47 PM
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * Description: 根据主键查询
     *
     * @param id id
     * @return T t
     * @author shaoqiping
     * @date 6/16/21 1:48 PM
     */
    T selectByPrimaryKey(Long id);

    /**
     *
     * Description: 根据条件查询列表
     *
     * @param params 条件
     * @return java.util.List<T>
     * @author shaoqiping
     * @date 6/16/21 1:48 PM
     */
    List<T> selectList(Map<String, Object> params);

    /**
     *
     * Description: 动态新增
     *
     * @param t t
     * @return int
     * @author shaoqiping
     * @date 6/16/21 1:49 PM
     */
    int insertSelective(T t);

    /**
     *
     * Description: 动态修改
     *
     * @param t t
     * @return int
     * @author shaoqiping
     * @date 6/16/21 1:50 PM
     */
    int updateByPrimaryKeySelective(T t);

    /**
     *
     * Description: 根据条件查询总条数
     *
     * @param pageInfo page
     * @return java.lang.Long
     * @author shaoqiping
     * @date 6/30/21 3:36 PM
     */
    Long count(PageUtil<T> pageInfo);

    /**
     *
     * Description: 根据条件分页查询
     *
     * @param pageInfo page
     * @return java.util.List<T>
     * @author shaoqiping
     * @date 6/30/21 3:35 PM
     */
    List<T> selectPage(PageUtil<T> pageInfo);

}
