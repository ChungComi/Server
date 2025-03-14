package ChungComiServer.dot.global.security;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Slf4j
public class UserContext {

    private Long userId;

//    @PostConstruct
//    private void init(){
//        log.info("웹 스코프 빈 생성");
//    }

//    @PreDestroy
//    private void destroy(){
//        log.info("웹 스코프 빈 삭제");
//    }

    public void saveUserIdAtInterceptor(String userId){
        this.userId =Long.valueOf(userId);
    }
}
