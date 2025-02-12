package ChungComiServer.dot.global.security;

import ChungComiServer.dot.api.service.NewsService;
import ChungComiServer.dot.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class Config {

    private final MemberRepository memberRepository;

    @Bean
    public NewsService techStackNewsService(){
        return new NewsService(restTemplate(),memberRepository);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
