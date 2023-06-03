package com.eightbit.biz.user;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
                .httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/Users/**", "/Board/**").permitAll()
                .antMatchers(HttpMethod.POST, "/Users/**").permitAll()
                .antMatchers(HttpMethod.POST, "/Board/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/Users/**","/Board/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/Users/**", "/Board/**").authenticated()
                .anyRequest().permitAll().and()
                .formLogin().disable();


    }
}
