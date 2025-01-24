package com.tqz.datapermission.core.rule;

import com.tqz.datapermission.core.rule.dept.DeptDataPermissionRuleV1;

/**
 * {@link DeptDataPermissionRuleV1} 的自定义配置接口
 *
 * @author 芋道源码
 */
@FunctionalInterface
public interface DeptDataPermissionRuleCustomizerV1 {

    /**
     * 自定义该权限规则
     * 1. 调用 {@link DeptDataPermissionRuleV1#addDeptColumn(Class, String)} 方法，配置基于 dept_id 的过滤规则
     * 2. 调用 {@link DeptDataPermissionRuleV1#addUserColumn(Class, String)} 方法，配置基于 user_id 的过滤规则
     *
     * @param rule 权限规则
     */
    void customize(DeptDataPermissionRuleV1 rule);

}
