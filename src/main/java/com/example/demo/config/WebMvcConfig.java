package com.example.demo.config;

import cn.hutool.core.util.StrUtil;
import com.example.demo.interceptor.CurrentUserInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author haojie.cui
 * @since 2021/12/29 11:43
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${oauth2.ignore-path}")
    private String ignorePath;


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOrigins("*")
                // 是否允许证书 不再默认开启
                .allowCredentials(true)
                // 设置允许的方法
                .allowedMethods("*")
                // 跨域允许时间
                .maxAge(3600);
    }

    /**
     * 通用拦截器排除swagger设置，所有拦截器都会自动加swagger相关的资源排除信息
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(currentUserInterceptor())
                .excludePathPatterns(StrUtil.split(ignorePath, ","))
                .excludePathPatterns(StrUtil.split("/index.html,/login,/swagger-ui.html,/actuator/health,/webjars/**,/v3/**,/swagger**/**,/doc.html,/health,/favicon.ico", ","))
                .excludePathPatterns("/demo")
                .excludePathPatterns("/error")
                .excludePathPatterns(StrUtil.split("/assets/img/favicon.png,/assets/**", ","));
    }

    @Bean
    public CurrentUserInterceptor currentUserInterceptor() {
        return new CurrentUserInterceptor();
    }
}