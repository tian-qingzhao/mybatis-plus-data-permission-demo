package com.tqz.datapermission.v2.annotation;

import com.tqz.datapermission.v2.config.MybatisPlusConfiguration;
import com.tqz.datapermission.v2.config.SpringBeanAutoConfigurationV2;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启数据权限功能v2版本的注解.
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2025/2/10 10:35
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({MybatisPlusConfiguration.class,
        SpringBeanAutoConfigurationV2.class})
public @interface EnableDataPermissionV2 {
}
