package ChungComiServer.dot.core.controller;

import ChungComiServer.dot.core.dto.LoginDTO;
import ChungComiServer.dot.core.dto.RegisterDTO;
import ChungComiServer.dot.core.service.AuthService;
import ChungComiServer.dot.global.response.ErrorCode;
import ChungComiServer.dot.global.response.Response;
import ChungComiServer.dot.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.InvalidPropertiesFormatException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/sign-in")
    public Response login(@RequestBody LoginDTO loginDTO){
        try{
            String username = authService.login(loginDTO.getLoginID(),loginDTO.getLoginPW());
            if(!username.isEmpty()) {
                String token = jwtUtil.generateToken(username);
                return Response.success(token);
            }
            return Response.failure(new ErrorCode("회원가입 x"));
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @PostMapping("/sign-up")
    public Response register(@RequestBody RegisterDTO registerDTO){
        try{
            Long registeredId = authService.register(registerDTO.getName(), registerDTO.getLoginId(), registerDTO.getLoginPw(), registerDTO.getMemberCompanies(), registerDTO.getMemberTechStacks());
            return Response.success(registeredId);
        } catch (InvalidPropertiesFormatException e) {
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }


}
