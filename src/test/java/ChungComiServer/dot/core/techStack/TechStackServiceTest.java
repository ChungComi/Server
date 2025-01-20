package ChungComiServer.dot.core.techStack;

import ChungComiServer.dot.core.ServiceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@SpringBootTest
@Transactional
public class TechStackServiceTest extends ServiceTest {

    @Nested
    class 기술스택_조회는{
        @Test
        public void 모든_기술스택_조회() throws Exception {
            //given
            //when

            //then
            org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class,()->
                    techStackService.findAll());
        }

        @Test
        public void 아이디로_기술스택_조회() throws Exception {
            //given

            //when

            //then
            org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class,()->
                    techStackService.findById("1"));
        }

        @Test
        public void 이름으로_기술스택_조회() throws Exception {
            //given

            //when

            //then
            org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class,()->
                    techStackService.findByName("techName"));
        }
    }

    @Test
    public void 기술스택_변경() throws Exception {
        //given
        //when

        //then
        Assertions.assertThrows(NoSuchElementException.class,()->
                techStackService.modifyTechInfo("1","techName","description")
        );
    }
}
