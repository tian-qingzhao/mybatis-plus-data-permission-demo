package com.tqz.datapermission.v2.config;

import com.tqz.controller.v2.SaTokenLoginController;
import com.tqz.controller.v2.SysUserController;
import com.tqz.datapermission.v2.core.service.SysDataScopeServiceImpl;
import com.tqz.datapermission.v2.core.service.SysUserServiceImpl;
import org.springframework.context.annotation.Bean;

/**
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2025/2/10 15:35
 */
public class SpringBeanAutoConfigurationV2 {

    @Bean("sdss")
    public SysDataScopeServiceImpl sysDataScopeService() {
        return new SysDataScopeServiceImpl();
    }

    @Bean
    public SysUserServiceImpl sysUserService() {
        return new SysUserServiceImpl();
    }

    @Bean
    public SysUserController sysUserController() {
        return new SysUserController();
    }

    @Bean
    public SaTokenLoginController saTokenLoginController() {
        return new SaTokenLoginController();
    }
}
