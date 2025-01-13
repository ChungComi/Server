package ChungComiServer.dot.global.security;

import ChungComiServer.dot.global.security.util.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter implements Filter {

    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest instanceof HttpServletRequest request
                && servletResponse instanceof HttpServletResponse response){
            String token = request.getHeader("Authorization");

            //인증하지 않아도 사용 가능한 경로 처리하기
            String requestURI = request.getRequestURI();
            if(isNoNeedAuth(requestURI)){
                filterChain.doFilter(request,response);
                return;
            }

            //인증해야 사용 가능한 경로 처리하기
            if(token != null && !token.isEmpty()){
                try{
                    String username = jwtUtil.validateToken(token.replace("Bearer",""));
                    request.setAttribute("username",username);
                } catch (SecurityException e){
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("유효하지 않거나 만료된 토큰입니다.");
                    return;
                }
            }

            filterChain.doFilter(request,response);
        }


    }

    private boolean isNoNeedAuth(String requestURI) {
        if(requestURI.startsWith("/auth")){
            return true;
        }
        return false;
    }
}
