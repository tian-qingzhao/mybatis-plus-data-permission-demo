package com.tqz;

import com.tqz.datapermission.v1.annotation.EnableDataPermissionV1;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类.
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2025/1/23 14:39
 */
@SpringBootApplication
@MapperScan("com.tqz.mapper")
@EnableDataPermissionV1
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
