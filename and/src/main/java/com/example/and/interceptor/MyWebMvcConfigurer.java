package com.example.and.interceptor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;


@Configuration
@ConfigurationProperties(prefix = "loginignore")
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    //从配置文件取出不需要拦截的url
    private List<String> ignoreURLs = new ArrayList<>();

    public void setIgnoreURLs(List<String> ignoreURLs) {
        this.ignoreURLs = ignoreURLs;
    }

    @Bean
    public PermissionInterceptor permissionInterceptor() {
        return new PermissionInterceptor();
    }

    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //将静态文件目录加入ignore文件夹
        //由于不知道webjars的具体目录，现将具体访问url直接放入忽略目录
        ignoreURLs.add("/webjars/**");
        registry.addInterceptor(tokenInterceptor()).excludePathPatterns(ignoreURLs);
        //permissionInterceptor只放入了/webjars/**的静态资源目录，否则静态资源也要进行鉴权
        registry.addInterceptor(permissionInterceptor()).excludePathPatterns("/webjars/**");
    }

}