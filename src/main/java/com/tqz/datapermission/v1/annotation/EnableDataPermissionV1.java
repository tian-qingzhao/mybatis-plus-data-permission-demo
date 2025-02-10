package com.tqz.datapermission.v1.annotation;

import com.tqz.datapermission.v1.config.SpringBeanAutoConfigurationV1;
import com.tqz.datapermission.v1.config.YudaoDataPermissionAutoConfiguration;
import com.tqz.datapermission.v1.config.YudaoDeptDataPermissionAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启数据权限功能v1版本的注解.
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2025/2/10 10:35
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ SpringBeanAutoConfigurationV1.class,
        YudaoDataPermissionAutoConfiguration.class,
       YudaoDeptDataPermissionAutoConfiguration.class})
public @interface EnableDataPermissionV1 {
}
