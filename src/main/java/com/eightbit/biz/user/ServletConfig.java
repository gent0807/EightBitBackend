package com.eightbit.biz.user;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

    @Configuration
    @EnableWebMvc  // <annotation-driven/>
    @ComponentScan(basePackages = { "com.eightbit.view_controller", "com.eightbit.biz.common.exception" })
    public class ServletConfig implements WebMvcConfigurer {

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry){
            registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        }

        @Override
        public void configureViewResolvers(ViewResolverRegistry registry){
            InternalResourceViewResolver bean=new InternalResourceViewResolver();
            bean.setViewClass(JstlView.class);
            bean.setPrefix("/WEB-INF/views/");
            bean.setSuffix(".jsp");
            registry.viewResolver(bean);
        }



}
