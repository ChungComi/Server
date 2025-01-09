package ChungComiServer.dot.global.security;

import ChungComiServer.dot.global.security.util.JwtUtil;
import ChungComiServer.dot.global.security.util.PasswordUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

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
        return new JwtUtil();
    }
}
