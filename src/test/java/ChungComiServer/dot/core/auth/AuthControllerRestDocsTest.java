package ChungComiServer.dot.core.auth;

import ChungComiServer.dot.core.controller.AuthController;
import ChungComiServer.dot.core.dto.auth.LoginDTO;
import ChungComiServer.dot.core.dto.auth.RegisterDTO;
import ChungComiServer.dot.core.service.AuthService;
import ChungComiServer.dot.global.response.Response;
import ChungComiServer.dot.global.security.SecurityConfig;
import ChungComiServer.dot.global.security.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@Import(SecurityConfig.class)
@AutoConfigureRestDocs(outputDir = "build/snippets/auth")
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private JwtUtil jwtUtil;

    @Test
    @DisplayName("로그인 성공 테스트")
    void loginSuccess() throws Exception {
        LoginDTO loginDTO = new LoginDTO("testUser", "password123");

        when(authService.login("testUser", "password123")).thenReturn("testUser");
        when(jwtUtil.generateToken("testUser")).thenReturn("mockedToken");

        mockMvc.perform(post("/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.result.data").value("mockedToken"))
                .andDo(document("logIn"));
    }

    @Test
    @DisplayName("로그인 실패 - 아이디 또는 비밀번호 미입력")
    void loginFailureValidation() throws Exception {
        LoginDTO loginDTO = new LoginDTO("", "");

        mockMvc.perform(post("/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.result.message").value("아이디나 비밀번호를 입력하지 않음"));
    }

    @Test
    @DisplayName("회원가입 성공 테스트")
    void registerSuccess() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO("name", "testUser", "password123");

        when(authService.register("name", "testUser", "password123")).thenReturn(1L);

        mockMvc.perform(post("/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.result.data").value(1L))
                .andDo(document("register"));
    }

    @Test
    @DisplayName("회원가입 실패 - 입력값 검증 실패")
    void registerFailureValidation() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO("", "", "");

        mockMvc.perform(post("/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.result.message").exists());
    }


}
