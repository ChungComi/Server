package ChungComiServer.dot.core.controller;

import ChungComiServer.dot.core.dto.LoginDTO;
import ChungComiServer.dot.core.dto.RegisterDTO;
import ChungComiServer.dot.core.service.AuthService;
import ChungComiServer.dot.global.response.ErrorCode;
import ChungComiServer.dot.global.response.Response;
import ChungComiServer.dot.global.security.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public Response login(@Valid @RequestBody LoginDTO loginDTO, BindingResult result){
        try{
            if(result.hasErrors()){
                return Response.failure(new ErrorCode("아이디나 비밀번호를 입력하지 않음"));
            }
            String username = authService.login(loginDTO.getLoginID(),loginDTO.getLoginPW());
            String token = jwtUtil.generateToken(username);
            return Response.success(token);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @PostMapping("/sign-up")
    public Response register(@Valid @RequestBody RegisterDTO registerDTO, BindingResult result){
        try{
            if(result.hasErrors()){
                StringBuilder stringBuilder = new StringBuilder();
                result.getFieldErrors().forEach(fieldError -> stringBuilder.append(fieldError.getDefaultMessage()));
            }
            Long registeredId = authService.register(registerDTO.getName(), registerDTO.getLoginId(), registerDTO.getLoginPw(), registerDTO.getMemberCompanies(), registerDTO.getMemberTechStacks());
            return Response.success(registeredId);
        } catch (InvalidPropertiesFormatException e) {
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }


}
