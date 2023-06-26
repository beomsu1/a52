package org.zerock.a52.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.zerock.a52.security.handler.CustomAccessDeniedHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Configuration // 설정파일 대신에 사용하는 거다!
@Log4j2
@EnableMethodSecurity
@RequiredArgsConstructor
public class CustomSecurityConfig {

    // 의존성 주입
    private final DataSource dataSource;

    // Spring Security 구성에서 사용되며, 사용자의 로그인 기억 기능을 지원하기 위해 필요한 객체
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
    JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
    repo.setDataSource(dataSource);
    return repo;
}
    
    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http)throws Exception {
    log.info("-----------------------configuration---------------------");

    // http.formLogin(Customizer.withDefaults()); 


    // //login 경로 로그인 페이지 띄우기
    http.formLogin(config ->{
        config.loginPage("/member/signin"); 
        // 앞으로 로그인은 다 이경로를 쓸 거야! 라는 뜻
    });

    http.exceptionHandling(config ->
    config.accessDeniedHandler(new CustomAccessDeniedHandler()));

    // 로그인 유지
    http.rememberMe(config ->{
        
        // remeberMe 기능을 사용하기 위해 persistentTokenRepository를 설정
        config.tokenRepository(persistentTokenRepository());

        // 토큰을 얼마나 유지되게 만들거?
        config.tokenValiditySeconds(60*60*24*7);
        
    });
    
    // csrf 토큰 비활성화
    http.csrf(config ->{
        config.disable();
    });

    return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();   
     }

}
