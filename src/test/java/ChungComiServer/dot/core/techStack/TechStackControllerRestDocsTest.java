package ChungComiServer.dot.core.techStack;

import ChungComiServer.dot.core.controller.TechStackController;
import ChungComiServer.dot.core.dto.interest.ModifyInterestDTO;
import ChungComiServer.dot.core.dto.interest.ResponseInterestDTO;
import ChungComiServer.dot.core.dto.post.ResponsePostDTO;
import ChungComiServer.dot.core.entity.Post;
import ChungComiServer.dot.core.entity.interest.Company;
import ChungComiServer.dot.core.entity.interest.Interest;
import ChungComiServer.dot.core.entity.interest.TechStack;
import ChungComiServer.dot.core.service.PostService;
import ChungComiServer.dot.core.service.TechStackService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TechStackController.class)
@AutoConfigureRestDocs(outputDir = "build/snippets/tech-stack")
@AutoConfigureMockMvc(addFilters = false) // 시큐리티 필터 비활성화
public class TechStackControllerRestDocsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TechStackService techStackService;

    @Test
    public void 모든_기술스택을_조회한다() throws Exception {
        //given
        List<ResponseInterestDTO> techStacks = List.of(
                new ResponseInterestDTO(new TechStack()),
                new ResponseInterestDTO(new TechStack()),
                new ResponseInterestDTO(new TechStack())
        );
        when(techStackService.findAll()).thenReturn(techStacks);
        //when
        this.mockMvc.perform(get("/tech-stack"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.result.data").isArray())
        //then
                .andDo(document("all"));
    }

    @Test
    public void 아이디로_기술스택을_조회한다() throws Exception {
        //given
        when(techStackService.findById("1"))
                .thenReturn(new ResponseInterestDTO(new TechStack()));
        //when
        this.mockMvc.perform(get("/tech-stack/{tech-stackId}","1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.data.name").value(nullValue()))
        //then
                .andDo(document("byId"));
    }

    @Test
    public void 이름으로_기술스택을_조회한다() throws Exception {
        //given
        List<ResponseInterestDTO> techStacks = List.of(
                new ResponseInterestDTO(new TechStack()),
                new ResponseInterestDTO(new TechStack()),
                new ResponseInterestDTO(new TechStack())
        );
        when(techStackService.findByName("stackName"))
                .thenReturn(techStacks);
        //when
        this.mockMvc.perform(get("/tech-stack/name/{tech-stackName}","techStack"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.data").isArray())
        //then
                .andDo(document("byName"));
    }

    @Test
    @DisplayName("기술 스택 정보 수정")
    public void 기술스택_정보_수정() throws Exception {
        // given
        String techStackId = "1";
        ModifyInterestDTO modifyInterestDTO = new ModifyInterestDTO();
        modifyInterestDTO.setName("Updated Name");
        modifyInterestDTO.setDescription("Updated Description");
        ResponseInterestDTO responseInterestDTO = new ResponseInterestDTO(new TechStack("Updated Name", "Updated Description"));

        when(techStackService.modifyTechInfo(techStackId, modifyInterestDTO.getName(), modifyInterestDTO.getDescription()))
                .thenReturn(responseInterestDTO);

        // when & then
        this.mockMvc.perform(put("/tech-stack/{tech-stackId}", techStackId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(modifyInterestDTO))) // JSON 변환
                .andExpect(status().isOk()) // 응답 상태 확인
                .andExpect(jsonPath("$.success").value(true)) // 성공 여부 확인
                .andExpect(jsonPath("$.result.data.name").value("Updated Name")) // 수정된 이름 확인
                .andExpect(jsonPath("$.result.data.description").value("Updated Description")) // 수정된 설명 확인
                .andDo(print())
                .andDo(document("modify-tech-info")); // REST Docs 문서화
    }

}
