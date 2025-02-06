package com.tqz.datapermission.v1.core.rule;

import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.tqz.entity.BaseDO;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2025/1/24 11:32
 */
public abstract class AbstractDataPermissionRule implements DataPermissionRule {

    /**
     * 所有表名
     */
    protected final Set<String> TABLE_NAMES = new HashSet<>();

    protected final Map<String, String> TABLE_NAME_AND_COLUMN_NAMES = new HashMap<>();

    @Override
    public Set<String> getTableNames() {
        return TABLE_NAMES;
    }

    @Override
    public void addRule(Class<? extends BaseDO> entityClass) {
        String tableName = TableInfoHelper.getTableInfo(entityClass).getTableName();
        TABLE_NAME_AND_COLUMN_NAMES.put(tableName, "id");
        TABLE_NAMES.add(tableName);
    }

    @Override
    public void addRule(Class<? extends BaseDO> entityClass, String columnName) {
        String tableName = TableInfoHelper.getTableInfo(entityClass).getTableName();
        TABLE_NAME_AND_COLUMN_NAMES.put(tableName, columnName);
        TABLE_NAMES.add(tableName);
    }
}
