package com.tqz.datapermission.core.rule;

/**
 * 数据权限规则定制器.
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2025/1/24 10:14
 */
public interface DataPermissionRuleCustomizer {

    void customize(DataPermissionRule rule);
}
