package com.tqz.datapermission.v1.config;

import com.tqz.controller.v1.DeptController;
import com.tqz.controller.v1.UserController;
import com.tqz.datapermission.v1.core.service.DeptService;
import com.tqz.datapermission.v1.core.service.UserService;
import com.tqz.datapermission.v1.core.service.impl.DeptServiceImpl;
import com.tqz.datapermission.v1.core.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;

/**
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2025/2/10 15:37
 */
public class SpringBeanAutoConfigurationV1 {

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public DeptService deptService() {
        return new DeptServiceImpl();
    }

    @Bean
    public UserController userController() {
        return new UserController();
    }

    @Bean
    public DeptController deptController() {
        return new DeptController();
    }
}
