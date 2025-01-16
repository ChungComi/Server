package ChungComiServer.dot.core.controller;

import ChungComiServer.dot.core.dto.interest.ResponseInterestDTO;
import ChungComiServer.dot.core.entity.interest.Company;
import ChungComiServer.dot.core.service.CompanyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@WebMvcTest(CompanyController.class)
@AutoConfigureRestDocs(outputDir = "build/snippets")
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CompanyService companyService;

    @Test
    @DisplayName("모든 기업의 이름, 평점, 댓글을 조회한다.")
    public void 모든_기업_조회() throws Exception {
        //given
        Company company = new Company();
        Company company2 = new Company();

//        given(companyService.findAll())
//                .willReturn()
        //when

        //then
    }

}