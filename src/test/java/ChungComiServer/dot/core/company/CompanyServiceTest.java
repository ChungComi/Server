package ChungComiServer.dot.core.company;

import ChungComiServer.dot.core.ServiceTest;
import ChungComiServer.dot.core.dto.interest.RegisterInterestDTO;
import ChungComiServer.dot.core.dto.interest.ResponseInterestDTO;
import ChungComiServer.dot.core.entity.interest.Company;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
@Transactional
public class CompanyServiceTest extends ServiceTest {

    @Nested
    class 회사추가는{
        @DisplayName("잘못된 회사 추가하는 로직")
        @ParameterizedTest
        @MethodSource("generateInvalidArguments")
        void 잘못된_회사_추가(String name, String description) {
            //given
            RegisterInterestDTO company = new RegisterInterestDTO();
            company.setName(name);
            company.setDescription(description);

            //when
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            //then
            var violations = validator.validate(company);
            org.junit.jupiter.api.Assertions.assertFalse(violations.isEmpty(), "유효성 검사가 실패해야 합니다.");
        }

        @DisplayName("회사를 추가하는 로직")
        @ParameterizedTest
        @MethodSource("generateArguments")
        void 회사_추가(String name1,String description1, String name2, String description2 ) throws Exception {
            //given
            Company companyA = new Company(name1,description1);
            Company companyB = new Company(name2,description2);

            //when
            companyService.register(companyA.getName(),companyA.getDescription());
            companyService.register(companyB.getName(),companyB.getDescription());

            //then
            Assertions.assertThat(companyService.findAll().size()).isNotZero();
        }

        private static Stream<Arguments> generateInvalidArguments(){
            return Stream.of(
                    Arguments.of(null,null),
                    Arguments.of(null,"description")
            );
        }

        private static Stream<Arguments> generateArguments(){
            return Stream.of(
                    Arguments.of("companyA","descriptionA","companyB","descriptionB"),
                    Arguments.of("companyA",null,"companyB",null)
            );
        }
    }


    @Nested
    class 회사반환은{
        @ParameterizedTest
        @MethodSource("generateArguments")
        @DisplayName("저장된 모든 회사를 반환하는 로직")
        public void 모든_회사_반환(String nameA,String descriptionA,String nameB,String descriptionB) throws Exception {
            //given
            Company companyA = new Company(nameA,descriptionA);
            Company companyB = new Company(nameB,descriptionB);

            //when
            companyService.register(companyA.getName(), companyA.getDescription());
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

        @Test
        @DisplayName("아이디로 회사 검색")
        public void 아이디로_회사_반환() throws Exception {
            //given
            Company companyA = new Company("companyA","descriptionA");

            //when
            Long registeredId = companyService.register(companyA.getName(), companyA.getDescription());

            //then
            Assertions.assertThat(companyService.findById(String.valueOf(registeredId)).getName())
                    .isEqualTo(companyA.getName());

            Assertions.assertThat(companyService.findById(String.valueOf(registeredId)).getDescription())
                    .isEqualTo(companyA.getDescription());
        }


        @Test
        @DisplayName("회사 이름으로 회사 리스트 반환")
        public void 이름으로_회사_반환() throws Exception {
            //given
            Company companyA = new Company("companyA","descriptionA");

            //when
            Long registeredId = companyService.register(companyA.getName(), companyA.getDescription());

            //then
            Assertions.assertThat(companyService.findByName("companyA"))
                    .extracting(ResponseInterestDTO::getName)
                    .containsExactlyInAnyOrder(companyA.getName());
        }

        private static Stream<Arguments> generateArguments(){
            return Stream.of(
                    Arguments.of("companyA","descriptionA","companyB","descriptionB"),
                    Arguments.of("companyA",null,"companyB",null)
            );
        }
    }


    @Test
    @DisplayName("회사 정보 수정 로직")
    public void 회사_정보_변경() throws Exception {
        //given
        Company companyA = new Company("companyA","descriptionA");

        //when
        Long registeredId = companyService.register(companyA.getName(), companyA.getDescription());
        companyService.modifyCompanyInfo(String.valueOf(registeredId),"modifyCompanyA","modifyDescriptionA");

        //then
        Assertions.assertThat(companyService.findById(String.valueOf(registeredId)).getName())
                .isNotEqualTo(companyA.getName());

        Assertions.assertThat(companyService.findById(String.valueOf(registeredId)).getDescription())
                .isNotEqualTo(companyA.getDescription());

        Assertions.assertThat(companyService.findById(String.valueOf(registeredId)).getName())
                .isEqualTo("modifyCompanyA");

        Assertions.assertThat(companyService.findById(String.valueOf(registeredId)).getDescription())
                .isEqualTo("modifyDescriptionA");

    }
    private static Stream<Arguments> generateArguments(){
        return Stream.of(
                Arguments.of("companyA","descriptionA","companyB","descriptionB"),
                Arguments.of("companyA",null,"companyB",null)
        );
    }

}
