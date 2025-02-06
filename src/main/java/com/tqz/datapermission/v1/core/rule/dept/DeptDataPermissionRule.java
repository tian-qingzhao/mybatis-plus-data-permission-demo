package com.tqz.datapermission.v1.core.rule.dept;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.tqz.datapermission.v1.core.dto.DeptDataPermissionRespDTO;
import com.tqz.datapermission.v1.core.rule.AbstractDataPermissionRule;
import com.tqz.datapermission.v1.core.rule.DataPermissionRule;
import com.tqz.datapermission.v1.core.util.MyBatisUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.ParenthesedExpressionList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 基于部门的 {@link DataPermissionRule} 数据权限规则实现
 *
 * @author 芋道源码
 */
@Slf4j
@AllArgsConstructor
public class DeptDataPermissionRule extends AbstractDataPermissionRule {

    @Override
    public Expression getExpression(String tableName, Alias tableAlias) {
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

        // 情况三，拼接 Dept 和 User 的条件，最后组合
        Expression deptExpression = buildDeptExpression(tableName, tableAlias, deptDataPermission.getDeptIds());
        if (deptExpression == null) {
            log.warn("[getExpression][Table({}/{}) DeptDataPermission({}) 构建的条件为空]", tableName, tableAlias, JSONUtil.toJsonStr(deptDataPermission));
            return EXPRESSION_NULL;
        } else {
            return deptExpression;
        }

        // 目前，如果有指定部门 + 可查看自己，采用 OR 条件。即，WHERE (dept_id IN ? OR user_id = ?)
        // return new ParenthesedExpressionList<>(new OrExpression(deptExpression, userExpression));
    }

    private Expression buildDeptExpression(String tableName, Alias tableAlias, Set<Long> deptIds) {
        // 如果不存在配置，则无需作为条件
        String columnName = TABLE_NAME_AND_COLUMN_NAMES.get(tableName);
        if (StrUtil.isEmpty(columnName)) {
            return null;
        }

        // 如果为空，则无条件
        if (CollUtil.isEmpty(deptIds)) {
            return null;
        }

        // 拼接条件
        List<LongValue> list;
        if (CollUtil.isEmpty(deptIds)) {
            list = new ArrayList<>();
        } else {
            list = deptIds.stream().map(LongValue::new).collect(Collectors.toList());
        }
        return new InExpression(MyBatisUtils.buildColumn(tableName, tableAlias, columnName),
                // Parenthesis 的目的，是提供 (1,2,3) 的 () 左右括号
                new ParenthesedExpressionList<>(new ExpressionList<>(list)));
    }

}
