package com.tqz.datapermission.v1.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.tqz.datapermission.v1.core.aop.DataPermissionAnnotationAdvisor;
import com.tqz.datapermission.v1.core.db.DataPermissionRuleHandler;
import com.tqz.datapermission.v1.core.rule.DataPermissionRule;
import com.tqz.datapermission.v1.core.rule.DataPermissionRuleFactory;
import com.tqz.datapermission.v1.core.rule.DataPermissionRuleFactoryImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据权限的自动配置类
 *
 * @author 芋道源码
 */
@MapperScan("com.tqz.datapermission.v1.core.mapper")
public class YudaoDataPermissionAutoConfiguration {

    @Bean
    public DataPermissionRuleFactory dataPermissionRuleFactory(List<DataPermissionRule> rules) {
        return new DataPermissionRuleFactoryImpl(rules);
    }

    @Bean
    public MybatisPlusInterceptor defaultMybatisPlusInterceptor(List<InnerInterceptor> innerInterceptorList) {
        MybatisPlusInterceptor plusInterceptor = new MybatisPlusInterceptor();
        plusInterceptor.setInterceptors(innerInterceptorList);

        // 添加分页插件
        plusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());

        return plusInterceptor;
    }

    @Bean
    public DataPermissionRuleHandler dataPermissionRuleHandler(MybatisPlusInterceptor interceptor, DataPermissionRuleFactory ruleFactory) {
        // 创建 DataPermissionInterceptor 拦截器
        DataPermissionRuleHandler handler = new DataPermissionRuleHandler(ruleFactory);
        DataPermissionInterceptor inner = new DataPermissionInterceptor(handler);
        // 添加到 interceptor 中
        // 需要加在首个，主要是为了在分页插件前面。这个是 MyBatis Plus 的规定
        List<InnerInterceptor> inners = new ArrayList<>(interceptor.getInterceptors());
        inners.add(0, inner);
        interceptor.setInterceptors(inners);
        return handler;
    }

    @Bean
    public DataPermissionAnnotationAdvisor dataPermissionAnnotationAdvisor() {
        return new DataPermissionAnnotationAdvisor();
    }

}
