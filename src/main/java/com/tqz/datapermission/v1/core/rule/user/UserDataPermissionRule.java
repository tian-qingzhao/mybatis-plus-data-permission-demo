package com.tqz.datapermission.v1.core.rule.user;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.tqz.datapermission.v1.core.dto.DeptDataPermissionRespDTO;
import com.tqz.datapermission.v1.core.rule.AbstractDataPermissionRule;
import com.tqz.datapermission.v1.core.util.MyBatisUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;

import java.util.HashSet;

/**
 * 用户数据权限规则.
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2025/1/24 10:35
 */
@Slf4j
public class UserDataPermissionRule extends AbstractDataPermissionRule {

    @Override
    public Expression getExpression(String tableName, Alias tableAlias) {
        // 用户id先写死
        Long userId = 1L;
        // 部门id先写死
        HashSet<Long> deptIds = CollectionUtil.newHashSet(1L, 2L, 3L, 4L);

        // 获得数据权限
        DeptDataPermissionRespDTO deptDataPermission = new DeptDataPermissionRespDTO();
        // 根据用户查询是否需要看到全部
        deptDataPermission.setAll(false);
        // 根据用户查询是否需要看到自己
        deptDataPermission.setSelf(true);
        deptDataPermission.setDeptIds(deptIds);

        // 情况一，如果是 ALL 可查看全部，则无需拼接条件
        if (deptDataPermission.getAll()) {
            return null;
        }

        // 情况二，即不能查看部门，又不能查看自己，则说明 100% 无权限
        if (CollUtil.isEmpty(deptDataPermission.getDeptIds()) && Boolean.FALSE.equals(deptDataPermission.getSelf())) {
            // WHERE null = null，可以保证返回的数据为空
            return new EqualsTo(null, null);
        }
        Expression expression = buildUserExpression(tableName, tableAlias, deptDataPermission.getSelf(), userId);

        if (expression == null) {
            log.warn("[getExpression][Table({}/{}) DeptDataPermission({}) 构建的条件为空]", tableName, tableAlias, JSONUtil.toJsonStr(deptDataPermission));
            return EXPRESSION_NULL;
        } else {
            return expression;
        }

        // 目前，如果有指定部门 + 可查看自己，采用 OR 条件。即，WHERE (dept_id IN ? OR user_id = ?)
        // return new ParenthesedExpressionList<>(new OrExpression(deptExpression, userExpression));
    }

    private Expression buildUserExpression(String tableName, Alias tableAlias, Boolean self, Long userId) {
        // 如果不查看自己，则无需作为条件
        if (Boolean.FALSE.equals(self)) {
            return null;
        }
        String columnName = TABLE_NAME_AND_COLUMN_NAMES.get(tableName);
        if (StrUtil.isEmpty(columnName)) {
            return null;
        }
        // 拼接条件
        return new EqualsTo(MyBatisUtils.buildColumn(tableName, tableAlias, columnName), new LongValue(userId));
    }
}
