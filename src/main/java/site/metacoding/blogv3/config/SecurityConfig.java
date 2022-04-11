package site.metacoding.blogv3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 해당 파일로 시큐리티가 활성화됨
@Configuration // IoC 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    // 인증 설정 메서드
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);
        http.csrf().disable(); // csrf 안 쓰면 postman 테스트 못함
        http.authorizeRequests()
                .antMatchers("/s/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .defaultSuccessUrl("/");
    }
}
