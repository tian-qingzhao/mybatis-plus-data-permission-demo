package com.tqz.datapermission.v1.annotation;

import com.tqz.datapermission.v1.config.DataPermissionAutoConfiguration;
import com.tqz.datapermission.v1.config.YudaoDataPermissionAutoConfiguration;
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
@Import({DataPermissionAutoConfiguration.class, YudaoDataPermissionAutoConfiguration.class})
public @interface EnableDataPermissionV1 {
}
