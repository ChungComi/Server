package ChungComiServer.dot.core.company;

import ChungComiServer.dot.core.ServiceTest;
import ChungComiServer.dot.core.dto.interest.ResponseInterestDTO;
import ChungComiServer.dot.core.entity.interest.Company;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class CompanyServiceTest extends ServiceTest {


    @Test
    public void 회사_추가() throws Exception {
        //given
        Company companyA = new Company("companyA","descriptionA");
        Company companyB = new Company("companyB","descriptionB");

        //when
        companyService.register(companyA.getName(),companyA.getDescription());
        companyService.register(companyB.getName(),companyB.getDescription());

        //then
        Assertions.assertThat(companyService.findAll().size()).isNotZero();
        Assertions.assertThat(companyService.findAll())
                .extracting(ResponseInterestDTO::getName)
                .containsExactlyInAnyOrder(companyA.getName(),companyB.getName());
        Assertions.assertThat(companyService.findAll())
                .extracting(ResponseInterestDTO::getDescription)
                .containsExactlyInAnyOrder(companyA.getDescription(), companyB.getDescription());

    }

//    @Test
    public void 모든_회사_반환() throws Exception {
        //given
        Company company = new Company("companyA","descriptionA");
        //when
        List<ResponseInterestDTO> companies = companyService.findAll();

        //then

    }

}
