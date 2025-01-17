package ChungComiServer.dot.core.controller;

import ChungComiServer.dot.core.dto.auth.LoginDTO;
import ChungComiServer.dot.core.dto.auth.RegisterDTO;
import ChungComiServer.dot.core.service.AuthService;
import ChungComiServer.dot.global.response.ErrorCode;
import ChungComiServer.dot.global.response.Response;
import ChungComiServer.dot.global.security.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.InvalidPropertiesFormatException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/sign-in")
    public Response login(@Valid @RequestBody LoginDTO loginDTO, BindingResult result){
        try{
            if(result.hasErrors()){
                return Response.failure(new ErrorCode("아이디나 비밀번호를 입력하지 않음"));
            }
            String username = authService.login(loginDTO.getLoginId(),loginDTO.getLoginPw());
            String token = jwtUtil.generateToken(username);
            return Response.success(token);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @PostMapping("/sign-up")
    public Response register(@Valid @RequestBody RegisterDTO registerDTO, BindingResult result){
        try{
            log.info("register 컨트롤러 실행됨 id={},pw={}",registerDTO.getLoginId(),registerDTO.getLoginPw());
            if(result.hasErrors()){
                StringBuilder stringBuilder = new StringBuilder();
                result.getFieldErrors().forEach(fieldError -> stringBuilder.append(fieldError.getDefaultMessage()));
            }
            Long registeredId = authService.register(registerDTO.getName(), registerDTO.getLoginId(), registerDTO.getLoginPw());
            return Response.success(registeredId);
        } catch (InvalidPropertiesFormatException e) {
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @GetMapping("")
    public void check(){
        log.info("왜 실행이 안되지?");
    }


}
