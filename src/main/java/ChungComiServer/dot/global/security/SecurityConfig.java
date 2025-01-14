package ChungComiServer.dot.global.security;

import ChungComiServer.dot.global.security.util.JwtUtil;
import ChungComiServer.dot.global.security.util.PasswordUtil;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Value("${EXPIRATION_TIME}") long expirationTime;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordUtil passwordUtil(BCryptPasswordEncoder bCryptPasswordEncoder){
        return new PasswordUtil(bCryptPasswordEncoder());
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil(Keys.secretKeyFor(SignatureAlgorithm.HS256),expirationTime);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationfilter(){
        return new JwtAuthenticationFilter(jwtUtil());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // 비활성화 의도를 명시적으로 선언
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 상태 비저장
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationfilter(), UsernamePasswordAuthenticationFilter.class); // JWT 필터 추가
        return http.build();
    }
}
