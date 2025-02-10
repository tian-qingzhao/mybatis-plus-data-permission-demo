package com.tqz.datapermission.v1.config;

import com.tqz.datapermission.v1.core.rule.DeptDataPermissionRuleCustomizer;
import com.tqz.datapermission.v1.core.rule.dept.DeptDataPermissionRule;
import com.tqz.datapermission.v1.core.entity.Dept;
import com.tqz.datapermission.v1.core.entity.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * 基于部门的数据权限 AutoConfiguration
 *
 * @author 芋道源码
 */
public class YudaoDeptDataPermissionAutoConfiguration {

    @Bean
    public DeptDataPermissionRuleCustomizer sysDeptDataPermissionRuleCustomizerV1() {
        return rule -> {
            // dept
            rule.addDeptColumn(User.class);
            rule.addDeptColumn(Dept.class, "id");
            // user
            rule.addUserColumn(User.class, "id");
        };
    }

    @Bean
    @ConditionalOnBean(value = DeptDataPermissionRuleCustomizer.class)
    public DeptDataPermissionRule deptDataPermissionRuleV1(List<DeptDataPermissionRuleCustomizer> customizers) {
        // 创建 DeptDataPermissionRule 对象
        DeptDataPermissionRule rule = new DeptDataPermissionRule();
        // 补全表配置
        customizers.forEach(customizer -> customizer.customize(rule));
        return rule;
    }

}
