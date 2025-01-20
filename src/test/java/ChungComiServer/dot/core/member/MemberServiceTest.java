package ChungComiServer.dot.core.member;

import ChungComiServer.dot.core.ServiceTest;
import ChungComiServer.dot.core.dto.auth.RegisterDTO;
import ChungComiServer.dot.core.dto.member.ResponseMemberDTO;
import ChungComiServer.dot.core.entity.Member;
import ChungComiServer.dot.core.service.AuthService;
import ChungComiServer.dot.core.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.InvalidPropertiesFormatException;
import java.util.stream.Stream;

@SpringBootTest
@Transactional
public class MemberServiceTest extends ServiceTest {


    @Nested
    class 회원_추가는{
        @DisplayName("잘못된 형식의 회원 추가 로직")
        @ParameterizedTest
        @MethodSource("generateInvalidArguments")
        public void 잘못된_회원_추가(String name,String loginId, String loginPw) throws Exception {
            //expect
            org.junit.jupiter.api.Assertions.assertThrows(InvalidPropertiesFormatException.class,()->
                    authService.register(name, loginId, loginPw));
        }

        @DisplayName("제대로 된 형식의 회원 추가 로직")
        @ParameterizedTest
        @MethodSource("generateValidArguments")
        public void 제대로된_회원_추가(String name,String loginId, String loginPw,
                               String name2,String loginId2, String loginPw2) throws Exception {
            //given
            Member member = new Member(name,loginId,loginPw);
            Member member2 = Member.builder().name(name2).loginId(loginId2).loginPw(loginPw2).build();

            //when
            authService.register(member.getName(),member.getLoginId(), member.getLoginPw());
            authService.register(member2.getName(), member2.getLoginId(), member2.getLoginPw());
            //then
            Assertions.assertThat(memberService.findAll().size()).isEqualTo(2);

        }

        private static Stream<Arguments> generateValidArguments(){
            return Stream.of(
                    Arguments.of("member1","login1","password1","member2","login2","password2"),
                    Arguments.of("member2","login2","password2","111","ofs2i","fusfsf12")
            );
        }

        private static Stream<Arguments> generateInvalidArguments(){
            return Stream.of(
                    Arguments.of("member1","login1","pa1"),
                    Arguments.of("member2","login2","pa"),
                    Arguments.of("member2","login2","2"),
                    Arguments.of("111","ofsi","fusf"),
                    Arguments.of("member2","login2","2i2910"),
                    Arguments.of("111","ofsi","fusfo")
            );
        }
    }
    @Nested
    class 회원_반환은{
        @DisplayName("등록된 모든 회원 조회")
        @ParameterizedTest
        @MethodSource("generateArguments")
        public void 모든_회원_조회(String name,String loginId, String loginPw,
                             String name2,String loginId2, String loginPw2) throws Exception {
            //given
            Member member = new Member(name,loginId,loginPw);
            Member member2 = Member.builder().name(name2).loginId(loginId2).loginPw(loginPw2).build();

            //when
            authService.register(member.getName(),member.getLoginId(), member.getLoginPw());
            authService.register(member2.getName(), member2.getLoginId(), member2.getLoginPw());

            //then
            Assertions.assertThat(memberService.findAll())
                    .extracting(ResponseMemberDTO::getName)
                    .containsExactlyInAnyOrder(member.getName(),member2.getName());

        }

        @DisplayName("등록된 회원 아이디로 조회")
        @Test
        public void 아이디로_회원_조회() throws Exception {
            //given
            Member member = new Member("name","loginId","loginPw");
            //when
            Long registeredId = authService.register(member.getName(), member.getLoginId(), member.getLoginPw());
            //then
            Assertions.assertThat(memberService.findById(String.valueOf(registeredId)).getName())
                    .isEqualTo(member.getName());

        }

        @DisplayName("등록된 회원 이름으로 조회")
        @ParameterizedTest
        @MethodSource("generateArguments")
        public void 이름으로_회원_조회(String name,String loginId, String loginPw,
                    String name2, String loginId2, String loginPw2) throws Exception {
            //given
            Member member = new Member(name,loginId,loginPw);
            Member member2 = Member.builder().name(name).loginId(loginId2).loginPw(loginPw2).build();

            //when
            authService.register(member.getName(),member.getLoginId(), member.getLoginPw());
            authService.register(member2.getName(), member2.getLoginId(), member2.getLoginPw());

            //then
            Assertions.assertThat(memberService.findByName(member.getName()))
                    .extracting(ResponseMemberDTO::getName)
                    .containsExactlyInAnyOrder(member.getName(), member2.getName());

        }

        private static Stream<Arguments> generateArguments(){
            return Stream.of(
                    Arguments.of("member1","login1","password1","member2","login9","password2"),
                    Arguments.of("member2","login2","password2","111","ofsfi","fusfsf2")
            );
        }
    }

}
