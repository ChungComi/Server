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
            if(token != null && !token.isEmpty()){
                try{
                    String username = jwtUtil.validateToken(token.replace("Bearer",""));
                    request.setAttribute("username",username);
                } catch (SecurityException e){
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Invalid or expired JWT token");
                    return;
                }
            }

            filterChain.doFilter(request,response);
        }


    }
}
