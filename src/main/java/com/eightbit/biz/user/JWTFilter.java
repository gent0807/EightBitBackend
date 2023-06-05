package com.eightbit.biz.user;

import com.eightbit.biz.user.impl.UserServiceImpl;
import com.eightbit.biz.user.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final UserServiceImpl userService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {



        final String authorization= request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(authorization);

        //Token을 보내지 않거나 올바른 형식이 아닌 경우 소거
        if(authorization==null || !authorization.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            System.out.println("xxxxxxxxxxxx");
            return;
        }
        //헤더의 AUTHORIZATION 필드에서 토큰 꺼내기
        String token=authorization.split(" ")[1];

        System.out.println(token);


        //Token 만료 여부 파악
        if(JWTUtil.isExpired(token, secretKey)){ //JWTUtil 클래스의 static 메소드
            filterChain.doFilter(request,response);
            return;
        }

        String userName=JWTUtil.getUserName(token, secretKey); //JWTUtil 클래스의 static 메소드

        String role="";

        if(userName.equals("TEMP0")){
            role="TEMP0";
        }
        else if(userName.equals("TEMP1")){
            role="TEMP1";
        }
        else if(userName.equals("TEMP2")){
            role="TEMP2";
        }
        else if(userName.equals("POSSIBLESIGN")){
            role="POSSIBLESIGN";
        }
        else{
            role=userService.findRoleFromNick(userName);
        }

        System.out.println(role);

        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userName, null, List.of(new SimpleGrantedAuthority(role)));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }

}


