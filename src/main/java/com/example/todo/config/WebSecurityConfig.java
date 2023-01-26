package com.example.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {

    //패스워드 인코딩 클래스를 등록
    //<bean id=? class=? / >
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    //시큐리티 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        //시큐리티 빌더
        http.cors() //크로스 오리진 정책
                .and()
                .csrf()//CSRF정책
                .disable()//사용 안함() 시큐리티에서 기본 설정 사용 안할거다
                .httpBasic().disable() //기본 시큐리티 인증 해체, 토큰 인증 쓸꺼기 때문에
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션 기반 인증 안함
                .and()
                .authorizeRequests().antMatchers("/","/api/auth/**").permitAll() //인증 요청중에서 '/' 경로랑 '/api/auth' 로 시작되는 경로는 인증하지 않고 모두 허용
                .anyRequest().authenticated(); //그 외의 모든 경로는 인증을 거쳐야함.
        return http.build();
    }
}
