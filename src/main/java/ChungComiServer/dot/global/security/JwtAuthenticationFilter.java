package ChungComiServer.dot.global.security;

import ChungComiServer.dot.global.security.util.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements Filter {

    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest instanceof HttpServletRequest request
                && servletResponse instanceof HttpServletResponse response){

            //인증하지 않아도 사용 가능한 경로 처리하기
            String requestURI = request.getRequestURI();
            if(isNoNeedAuth(requestURI)){
//                log.info("로그인 필터 인증 안해도 되면 호출되는 부분 uri={}",requestURI);
                filterChain.doFilter(request,response);
                return;
            }

            //인증해야 사용 가능한 경로 처리하기
            String token = request.getHeader("Authorization");
//            log.info("로그인 필터 인증 해야되면 호출되는 부분 uri ={}, token ={}",requestURI,token);
            if(token != null && !token.isEmpty()){
                try{
                    String tokenSubject = jwtUtil.validateToken(token.replace("Bearer ",""));
                    request.setAttribute("tokenSubject",tokenSubject);
                } catch (SecurityException e){
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("유효하지 않거나 만료된 토큰입니다.");
                }
            }
//            log.info("request.getAttribute('tokenSubject')={}",request.getAttribute("tokenSubject"));
            filterChain.doFilter(request,response);
        }
    }

    private boolean isNoNeedAuth(String requestURI) {
//        log.info("isNoNeedAuth method");
        return requestURI.startsWith("/auth") || requestURI.equals("/index.html") || requestURI.equals("/")
                ||requestURI.startsWith("/company") ||requestURI.startsWith("/tech-stack");
    }
}
