package com.tqz.datapermission.v2.core.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.reflect.GenericTypeUtils;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

import java.util.Collection;
import java.util.List;

/**
 * 自定义 Mapper 接口, 实现 自定义扩展
 *
 * @param <T> table 泛型
 * @param <V> vo 泛型
 * @author Lion Li
 * @since 2021-05-13
 */
@SuppressWarnings("unchecked")
public interface BaseMapperPlus<T, V> extends BaseMapper<T> {

    Log log = LogFactory.getLog(BaseMapperPlus.class);

    /**
     * 获取当前实例对象关联的泛型类型 V 的 Class 对象
     *
     * @return 返回当前实例对象关联的泛型类型 V 的 Class 对象
     */
    default Class<V> currentVoClass() {
        return (Class<V>) GenericTypeUtils.resolveTypeArguments(this.getClass(), BaseMapperPlus.class)[1];
    }

    /**
     * 获取当前实例对象关联的泛型类型 T 的 Class 对象
     *
     * @return 返回当前实例对象关联的泛型类型 T 的 Class 对象
     */
    default Class<T> currentModelClass() {
        return (Class<T>) GenericTypeUtils.resolveTypeArguments(this.getClass(), BaseMapperPlus.class)[0];
    }

    /**
     * 使用默认的查询条件查询并返回结果列表
     *
     * @return 返回查询结果的列表
     */
    default List<T> selectList() {
        return this.selectList(new QueryWrapper<>());
    }

    /**
     * 批量插入实体对象集合
     *
     * @param entityList 实体对象集合
     * @return 插入操作是否成功的布尔值
     */
    default boolean insertBatch(Collection<T> entityList) {
        return Db.saveBatch(entityList);
    }

    /**
     * 批量根据ID更新实体对象集合
     *
     * @param entityList 实体对象集合
     * @return 更新操作是否成功的布尔值
     */
    default boolean updateBatchById(Collection<T> entityList) {
        return Db.updateBatchById(entityList);
    }

    /**
     * 批量插入或更新实体对象集合
     *
     * @param entityList 实体对象集合
     * @return 插入或更新操作是否成功的布尔值
     */
    default boolean insertOrUpdateBatch(Collection<T> entityList) {
        return Db.saveOrUpdateBatch(entityList);
    }

    /**
     * 批量插入实体对象集合并指定批处理大小
     *
     * @param entityList 实体对象集合
     * @param batchSize  批处理大小
     * @return 插入操作是否成功的布尔值
     */
    default boolean insertBatch(Collection<T> entityList, int batchSize) {
        return Db.saveBatch(entityList, batchSize);
    }

    /**
     * 批量根据ID更新实体对象集合并指定批处理大小
     *
     * @param entityList 实体对象集合
     * @param batchSize  批处理大小
     * @return 更新操作是否成功的布尔值
     */
    default boolean updateBatchById(Collection<T> entityList, int batchSize) {
        return Db.updateBatchById(entityList, batchSize);
    }

    /**
     * 批量插入或更新实体对象集合并指定批处理大小
     *
     * @param entityList 实体对象集合
     * @param batchSize  批处理大小
     * @return 插入或更新操作是否成功的布尔值
     */
    default boolean insertOrUpdateBatch(Collection<T> entityList, int batchSize) {
        return Db.saveOrUpdateBatch(entityList, batchSize);
    }

}
