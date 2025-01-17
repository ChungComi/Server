package ChungComiServer.dot.core.company;

import ChungComiServer.dot.core.controller.CompanyController;
import ChungComiServer.dot.core.dto.interest.ResponseInterestDTO;
import ChungComiServer.dot.core.entity.interest.Company;
import ChungComiServer.dot.core.service.CompanyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
@AutoConfigureRestDocs(outputDir = "build/snippets/company")
@AutoConfigureMockMvc(addFilters = false) // 시큐리티 필터 비활성화
class CompanyControllerRestDocsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CompanyService companyService;

    @Test
    @DisplayName("모든 기업의 이름, 평점, 댓글을 조회한다.")
    public void 모든_기업_조회() throws Exception {
        //given
        List<Company> mockCompanies = List.of(
                new Company("companyA","descriptionA"),
                new Company("companyB","descriptionB")
        );
        List<ResponseInterestDTO> mockDatas  = mockCompanies.stream().map(ResponseInterestDTO::new).toList();
        when(companyService.findAll()).thenReturn(mockDatas);

        //when
        this.mockMvc.perform(get("/company"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("companyA")))
        //then
                .andDo(document("all"));
    }

    @Test
    @DisplayName("아이디를 통해 특정 기업의 이름, 평점, 댓글을 조회한다.")
    public void 아이디로_특정_기업_조회() throws Exception {
        //given
        ResponseInterestDTO mockCompany = new ResponseInterestDTO(new Company("companyA","descriptionA"));
        when(companyService.findById("1")).thenReturn(mockCompany);
        //when
        this.mockMvc.perform(get("/company/{companyId}",1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("companyA")))
        //then
                .andDo(document("byId"));
    }

    @Test
    @DisplayName("이름을 통해 특정 기업의 이름, 평점, 댓글을 조회한다.")
    public void 이름으로_특정_기업_조회() throws Exception{
        //given
        List<Company> mockCompanies = List.of(
                new Company("companyA","descriptionA"),
                new Company("companyA","descriptionA2")
        );
        List<ResponseInterestDTO> mockDatas  = mockCompanies.stream().map(ResponseInterestDTO::new).toList();
        when(companyService.findByName("companyA")).thenReturn(mockDatas);
        //when
        this.mockMvc.perform(get("/company/name/{companyName}","companyA"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("companyA")))
        //then
                .andDo(document("byName"));
    }

    @Test
    @DisplayName("새로운 기업을 등록한다.")
    public void 새로운_기업_등록() throws Exception{
        //given
        when(companyService.register("companyA","descriptionA")).thenReturn(1L);
        //when
        this.mockMvc.perform(post("/company/sign-up")
                        .contentType("application/json")
                        .content("{\"name\": \"companyA\", \"description\": \"descriptionA\"}"))
                .andDo(print())
                .andExpect(status().isOk())
        //then
                .andDo(document("register"));
    }

    @Test
    @DisplayName("아이디로 기업 정보를 변경한다.")
    public void 기업_정보_변경() throws Exception{
        //given
        ResponseInterestDTO mockCompany = new ResponseInterestDTO(new Company("companyA","descriptionA"));
        ResponseInterestDTO modifiedMockCompany = new ResponseInterestDTO(new Company("companyB","descriptionB"));

        when(companyService.modifyCompanyInfo("1","companyB","descriptionB"))
                .thenReturn(modifiedMockCompany);
        //when
        this.mockMvc.perform(put("/company/{companyId}",1)
                        .contentType("application/json")
                        .content("{\"name\": \"companyB\", \"description\": \"descriptionB\"}"))
                .andDo(print())
                .andExpect(status().isOk())
        //then
                .andDo(document("modify"));
    }

}