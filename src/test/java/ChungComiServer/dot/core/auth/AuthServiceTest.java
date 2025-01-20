package ChungComiServer.dot.core.auth;

import ChungComiServer.dot.core.service.AuthService;
import ChungComiServer.dot.core.service.MemberService;
import net.bytebuddy.implementation.bind.annotation.Argument;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@SpringBootTest
@Transactional
public class AuthServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private AuthService authService;

    @ParameterizedTest
    @MethodSource("generateArguments")
    public void 제대로된_회원가입_로그인(String name, String loginId,String loginPw) throws Exception {
        //given

        //when
        Long registeredId = authService.register(name, loginId, loginPw);

        //then
        Assertions.assertThat(authService.login(loginId,loginPw))
                .isEqualTo(name);

    }

    @ParameterizedTest
    @MethodSource("generateInvalidArguments")
    public void 잘못된_회원가입(String name, String loginId,String loginPw) throws Exception {
        //given

        //when

        //then
        org.junit.jupiter.api.Assertions.assertThrows(Exception.class,()->
                authService.register(name, loginId, loginPw)
        );

    }

    @ParameterizedTest
    @MethodSource("generateInvalidLoginArguments")
    public void 잘못된_로그인(String idParam, String pwParam) throws Exception {
        //given
        String name = "name";
        String loginId = "loginId";
        String loginPw = "loginPw";

        //when
        authService.register(name,loginId,loginPw);

        //then
        org.junit.jupiter.api.Assertions.assertThrows(Exception.class,() ->
                authService.login(idParam,pwParam));
    }

    private static Stream<Arguments> generateArguments(){
        return Stream.of(
                Arguments.of("name","loginId","loginPw"),
                Arguments.of("name2","loginId2","loginPw2")
        );
    }

    private static Stream<Arguments> generateInvalidLoginArguments(){
        return Stream.of(
                /**1, 2, 4는 컨트롤러 단에서 validate 실행 */
                Arguments.of("loginId",null),
                Arguments.of(null,"loginPw"),
                Arguments.of(null,null)
                );
    }

    private static Stream<Arguments> generateInvalidArguments(){
        return Stream.of(
                /**1, 2, 4는 컨트롤러 단에서 validate 실행 */
//                Arguments.of(null,"loginId","loginPw"),
//                Arguments.of("name2",null,"loginPw2"),
                Arguments.of("name2","loginId",null),
//                Arguments.of(null,null,"loginPw"),
                Arguments.of(null,"loginId",null),
                Arguments.of("name2",null,null),
                Arguments.of(null,null,null)
        );
    }
}
