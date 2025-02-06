package com.tqz.datapermission.v1.config;

import com.tqz.datapermission.v1.core.rule.DeptDataPermissionRuleCustomizerV1;
import com.tqz.datapermission.v1.core.rule.dept.DeptDataPermissionRuleV1;
import com.tqz.entity.Dept;
import com.tqz.entity.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * 基于部门的数据权限 AutoConfiguration
 *
 * @author 芋道源码
 */
//@Configuration
public class YudaoDeptDataPermissionAutoConfigurationV1 {

    @Bean
    public DeptDataPermissionRuleCustomizerV1 sysDeptDataPermissionRuleCustomizerV1() {
        return rule -> {
            // dept
            rule.addDeptColumn(User.class);
            rule.addDeptColumn(Dept.class, "id");
            // user
            rule.addUserColumn(User.class, "id");
        };
    }

    @Bean
    @ConditionalOnBean(value = DeptDataPermissionRuleCustomizerV1.class)
    public DeptDataPermissionRuleV1 deptDataPermissionRuleV1(List<DeptDataPermissionRuleCustomizerV1> customizers) {
        // 创建 DeptDataPermissionRule 对象
        DeptDataPermissionRuleV1 rule = new DeptDataPermissionRuleV1();
        // 补全表配置
        customizers.forEach(customizer -> customizer.customize(rule));
        return rule;
    }

}
