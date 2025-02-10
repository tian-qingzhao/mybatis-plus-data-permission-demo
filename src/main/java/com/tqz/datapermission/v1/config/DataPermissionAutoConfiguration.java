package com.tqz.datapermission.v1.config;

import com.tqz.datapermission.v1.core.rule.DataPermissionRuleCustomizer;
import com.tqz.datapermission.v1.core.rule.dept.DeptDataPermissionRule;
import com.tqz.datapermission.v1.core.rule.user.UserDataPermissionRule;
import com.tqz.entity.Dept;
import com.tqz.entity.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

/**
 * 数据权限自动配置类.
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2025/1/24 10:37
 */
//@Configuration
public class DataPermissionAutoConfiguration {

    @Bean
    public DataPermissionRuleCustomizer deptDataPermissionRuleCustomizer() {
        return rule -> {
            // dept
            rule.addRule(Dept.class);
            // user
            rule.addRule(User.class, "dept_id");
        };
    }

    @Bean
    @ConditionalOnBean(DataPermissionRuleCustomizer.class)
    public DeptDataPermissionRule deptDataPermissionRule(DataPermissionRuleCustomizer deptDataPermissionRuleCustomizer) {
        // 创建 DeptDataPermissionRule 对象
        DeptDataPermissionRule rule = new DeptDataPermissionRule();
        // 补全表配置
        deptDataPermissionRuleCustomizer.customize(rule);
        return rule;
    }

    @Bean
    public DataPermissionRuleCustomizer userDataPermissionRuleCustomizer() {
        return rule -> rule.addRule(User.class);
    }

    @Bean
    @ConditionalOnBean(DataPermissionRuleCustomizer.class)
    public UserDataPermissionRule userDataPermissionRule(DataPermissionRuleCustomizer userDataPermissionRuleCustomizer) {
        // 创建 DeptDataPermissionRule 对象
        UserDataPermissionRule rule = new UserDataPermissionRule();
        // 补全表配置
        userDataPermissionRuleCustomizer.customize(rule);
        return rule;
    }
}
