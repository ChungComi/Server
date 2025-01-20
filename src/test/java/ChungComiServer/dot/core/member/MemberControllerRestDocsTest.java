package ChungComiServer.dot.core.member;

import ChungComiServer.dot.core.controller.MemberController;
import ChungComiServer.dot.core.dto.member.ResponseMemberDTO;
import ChungComiServer.dot.core.entity.Member;
import ChungComiServer.dot.core.service.CompanyService;
import ChungComiServer.dot.core.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@AutoConfigureRestDocs(outputDir = "build/snippets/member")
@AutoConfigureMockMvc(addFilters = false) // 시큐리티 필터 비활성화
public class MemberControllerRestDocsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MemberService memberService;

    @Test
    @DisplayName("모든 회원을 조회한다")
    void 모든_회원_조회()throws Exception{
        //given
        List<ResponseMemberDTO> mockDatas = List.of(
                new ResponseMemberDTO(new Member("name1","loginId1","loginPw1")),
                new ResponseMemberDTO(new Member("name2","loginId2","loginPw2")),
                new ResponseMemberDTO(new Member("name3","loginId3","loginPw3"))
                );
        when(memberService.findAll()).thenReturn(mockDatas);
        //when
        this.mockMvc.perform(get("/member"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("name1")))
        //then
                .andDo(document("all"));
    }

    @Test
    @DisplayName("아이디로 회원을 조회한다")
    void 아이디로_회원_조회()throws Exception{
        //given
        ResponseMemberDTO mockDatas = new ResponseMemberDTO(new Member("name2","loginId2","loginPw2"));
        when(memberService.findById("1")).thenReturn(mockDatas);
        //when
        this.mockMvc.perform(get("/member/{memberId}","1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("name2")))
        //then
                .andDo(document("byId"));
    }


    @DisplayName("이름으로 회원을 조회한다")
    @Test
    public void 이름으로_회원_조회() throws Exception {
        //given
        List<ResponseMemberDTO> mockDatas = List.of(
                new ResponseMemberDTO(new Member("name2","loginId2","loginPw2")),
                new ResponseMemberDTO(new Member("name2","loginId3","loginPw2"))
        );
        when(memberService.findByName("name2")).thenReturn(mockDatas);
        //when
        this.mockMvc.perform(get("/member/name/{memberName}","name2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("name2")))
        //then
                .andDo(document("byName"));
    }

}
