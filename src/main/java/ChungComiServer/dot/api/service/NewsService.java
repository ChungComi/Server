package ChungComiServer.dot.api.service;

import ChungComiServer.dot.api.dto.SourceDTO;
import ChungComiServer.dot.api.dto.TechNews;
import ChungComiServer.dot.core.dto.member.MemberCompanyDTO;
import ChungComiServer.dot.core.dto.member.MemberTechStackDTO;
import ChungComiServer.dot.core.dto.member.ResponseMemberDTO;
import ChungComiServer.dot.core.entity.Member;
import ChungComiServer.dot.core.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional
public class NewsService {

    private static final String API_KEY = "98711ebabe53ab4a92e0ae0475f02dec48c498ba3944d87628e429b30658f0f7";
    private static final String BASE_URL = "https://serpapi.com/search.json";
    private final RestTemplate restTemplate;
    private final MemberRepository memberRepository;

    public NewsService(RestTemplate restTemplate, MemberRepository memberRepository) {
        this.restTemplate = restTemplate;
        this.memberRepository = memberRepository;

    }

    public List<TechNews> fetchMyTechStackNews(Long memberId) {

        Member foundMember = memberRepository.findById(memberId);
        ResponseMemberDTO memberDTO = new ResponseMemberDTO(foundMember);
        List<String> techStackList = memberDTO.getMemberTechStacks().stream().map(MemberTechStackDTO::getTechStackName).toList();
        techStackList.forEach(tech->log.info("@@@@@@@@@@@@@@@@={}",tech));
        List<TechNews> allNews = new ArrayList<>();

        for (String tech : techStackList) {
            log.info(tech);
            String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                    .queryParam("engine", "google_news")
                    .queryParam("q", tech) // 검색어: '기술명 + 개발'
                    .queryParam("gl", "kr")
                    .queryParam("hl", "ko")
                    .queryParam("when", "24h")
                    .queryParam("api_key", API_KEY)
                    .toUriString();

            log.info("@@@@@@@@@@@@@@@@유알엘 유알엘={}",url);
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            Map<String, Object> body = response.getBody();

            if (body.containsKey("news_results")) {
                List<Map<String, Object>> newsResults = (List<Map<String, Object>>) body.get("news_results");
                int count = 0;
                for (Map<String, Object> news : newsResults) {
                    if (count >= 4) break; // 최대 4개까지만 저장
                    String title = (String) news.getOrDefault("title", "제목 없음");
                    String link = (String) news.getOrDefault("link", "#");
                    Map<String, Object> sourceMap = (Map<String, Object>) news.getOrDefault("source", Map.of());
                    String source = sourceMap.getOrDefault("name", "출처 미상").toString();
                    String date = formatDate((String) news.getOrDefault("date", ""));
                    allNews.add(new TechNews(title, link, source, date));
                    count++;
                }
            }
        }
        allNews.forEach(techNews -> log.info(techNews.getTitle()));
        return allNews;
    }

    public List<TechNews> fetchMyCompanyNews(Long memberId) {

        Member foundMember = memberRepository.findById(memberId);
        ResponseMemberDTO memberDTO = new ResponseMemberDTO(foundMember);
        List<String> companyList = memberDTO.getMemberCompanies().stream().map(MemberCompanyDTO::getCompanyName).toList();
        companyList.forEach(tech->log.info("@@@@@@@@@@@@@@@@={}",tech));
        List<TechNews> allNews = new ArrayList<>();

        for (String company : companyList) {
            String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                    .queryParam("engine", "google_news")
                    .queryParam("q", company) // 검색어: '기술명 + 개발'
                    .queryParam("gl", "kr")
                    .queryParam("hl", "ko")
                    .queryParam("when", "24h")
                    .queryParam("api_key", API_KEY)
                    .toUriString();

            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            Map<String, Object> body = response.getBody();

            if (body.containsKey("news_results")) {
                List<Map<String, Object>> newsResults = (List<Map<String, Object>>) body.get("news_results");
                int count = 0;
                for (Map<String, Object> news : newsResults) {
                    if (count >= 4) break; // 최대 4개까지만 저장
                    String title = (String) news.getOrDefault("title", "제목 없음");
                    String link = (String) news.getOrDefault("link", "#");
                    Map<String, Object> sourceMap = (Map<String, Object>) news.getOrDefault("source", Map.of());
                    String source = sourceMap.getOrDefault("name", "출처 미상").toString();                    String date = formatDate((String) news.getOrDefault("date", ""));
                    allNews.add(new TechNews(title, link, source, date));
                    count++;
                }
            }
        }
        allNews.forEach(techNews -> log.info(techNews.getTitle()));
        return allNews;
    }

    private String formatDate(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return "날짜 미상";
        }
        String[] parts = dateString.split(",")[0].split("/"); // "01/08/2025, 08:00 AM, +0000 U" → ["01", "08", "2025"]
        if (parts.length == 3) {
            return parts[2] + "-" + parts[0] + "-" + parts[1]; // YYYY-MM-DD 형식으로 변환
        }
        return "날짜 형식 오류";
    }
}

