package ChungComiServer.dot.global.security;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@Getter
@Slf4j
public class UserContext {

    private String userId;

    @PostConstruct
    public void init(){
        log.info("웹 스코프 빈 생성");
    }

    @PreDestroy
    public void destroy(){
        log.info("웹 스코프 빈 삭제");
    }

    public void saveUserIdAtInterceptor(String userId){
        this.userId =userId;
    }
}
