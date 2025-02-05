package ChungComiServer.dot.global.security;

import ChungComiServer.dot.global.security.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;
    private final UserContext userContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        if(token != null && token.startsWith("Bearer ")){
            try{
                String userId = jwtUtil.validateToken(token.replace("Bearer",""));
                userContext.saveUserIdAtInterceptor(userId);
                log.info("Jwt에서 뽑아온 회원 id={}",userContext.getUserId());
            }catch (Exception e){
                log.info("JWT 토큰 인증 실패");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }else{
            log.warn("JWT 토큰이 없습니다.");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }
}
