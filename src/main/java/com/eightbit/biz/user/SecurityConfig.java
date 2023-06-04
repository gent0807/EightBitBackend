package com.eightbit.biz.user;

import com.eightbit.biz.user.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:auth.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    @Autowired
    @Qualifier("userService")
    private UserServiceImpl userService;

    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
                .httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/Users/**","/Board/**").permitAll()
                .antMatchers(HttpMethod.POST, "/Users/check/**","/Users/auth/**").permitAll()
                .antMatchers(HttpMethod.POST,"/Users/check/authkey").access("hasRole('TEMP1')")
                .antMatchers(HttpMethod.POST, "/Users/user/**").access("hasRole('TEMP2')")
                .antMatchers(HttpMethod.POST, "/Board/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/Users/**","/Board/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "Users/**","Board/**").authenticated().and()
                .addFilterBefore(new JWTFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class)
                .formLogin().disable();
    }
}
