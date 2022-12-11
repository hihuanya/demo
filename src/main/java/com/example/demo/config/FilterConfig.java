package com.example.demo.config;

import com.example.demo.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chuliuhuan
 * @date 2022-11-20 18:11
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AuthFilter> registAuth(){
        FilterRegistrationBean<AuthFilter> filterFilterRegistrationBean=new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new AuthFilter());//添加自己的过滤器
        filterFilterRegistrationBean.setName("token-Auth");
        filterFilterRegistrationBean.addUrlPatterns("/*");//拦截所有请求
        filterFilterRegistrationBean.setOrder(1);//优先执行，数月小，优先级越高
        return filterFilterRegistrationBean;
    }
}

