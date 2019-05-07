package com.example.and.interceptor;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/*
1) implements WebMvcConfigurer ： 不会覆盖@EnableAutoConfiguration关于WebMvcAutoConfiguration的配置
2) @EnableWebMvc + implements WebMvcConfigurer ： 会覆盖@EnableAutoConfiguration关于WebMvcAutoConfiguration的配置
3) extends WebMvcConfigurationSupport ：会覆盖@EnableAutoConfiguration关于WebMvcAutoConfiguration的配置
4) extends DelegatingWebMvcConfiguration ：会覆盖@EnableAutoConfiguration关于WebMvcAutoConfiguration的配置
 */

//@Configuration
@ConfigurationProperties(prefix = "loginignore")
public class MyWebMvcConfigurationSupport extends WebMvcConfigurationSupport {
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

    /**
     * 使用SpringBoot时遇到静态资源无法访问的问题，启用拦截器配置就会出现静态资源无法访问。
     * 发现如果继承了WebMvcConfigurationSupport，并且将文件加入配置 ，
     * 则在yml中配置的相关内容会失效。需要重新指定静态资源
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        registry.addResourceHandler("/**")
////                .addResourceLocations("classpath:/META-INF/resources/")
////                .addResourceLocations("classpath:/resources/")
////                .addResourceLocations("classpath:/static/")
////                .addResourceLocations("classpath:/public/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//        super.addResourceHandlers(registry);
//    }

    /**
     * 注册 拦截器 单独的servlet 将不会被过滤
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //将静态文件目录加入ignore文件夹
        //由于不知道webjars的具体目录，现将具体访问url直接放入忽略目录
        ignoreURLs.add("/webjars/**");
        registry.addInterceptor(tokenInterceptor()).excludePathPatterns(ignoreURLs);
        //permissionInterceptor只放入了/webjars/**的静态资源目录，否则静态资源也要进行鉴权
        registry.addInterceptor(permissionInterceptor()).excludePathPatterns("/webjars/**");
        super.addInterceptors(registry);
    }


    //以下解决出去乱码问题
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());

        //配置object转JSON
        //1.需要定义一个convert转换消息的对象;
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //2.添加fastJson的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //3处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        //4.在convert中添加配置信息.
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        //5.将convert添加到converters当中.
        converters.add(fastJsonHttpMessageConverter);

    }


//
//    @Override
//    public void configureContentNegotiation(
//            ContentNegotiationConfigurer configurer) {
//        configurer.favorPathExtension(false);
//    }

}
