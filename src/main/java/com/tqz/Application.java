package com.tqz;

import com.tqz.mybatis.interceptor.MybatisLogSqlInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 启动类.
 *
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2025/1/23 14:39
 */
@SpringBootApplication
//@EnableDataPermissionV1
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public MybatisLogSqlInterceptor mybatisLogSqlInterceptor() {
        return new MybatisLogSqlInterceptor();
    }
}
