package ChungComiServer.dot.api.service;

import ChungComiServer.dot.api.dto.GetMemberCompaniesDTO;
import ChungComiServer.dot.core.entity.Member;
import ChungComiServer.dot.api.repository.MemberApiRepository;
import ChungComiServer.dot.core.entity.School;
import ChungComiServer.dot.core.repository.MemberRepository;
import ChungComiServer.dot.core.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
@Slf4j
public class MemberApiService {

    private final MemberApiRepository memberApiRepository;
    private final MemberRepository memberRepository;
    private final SchoolRepository schoolRepository;

    public GetMemberCompaniesDTO findMemberCompaniesById(Long memberId) {
        Member memberCompanies = memberApiRepository.findMemberCompanies(memberId);
        if(memberCompanies== null)
            throw new NoSuchElementException("멤버가 존재하지 않음");
        return new GetMemberCompaniesDTO(memberCompanies);

    }

    @Transactional(readOnly = false)
    public Long registerSchool(Long userId, String name) {
        School school = new School(name);
        schoolRepository.save(school);
        Member member = memberRepository.findById(userId);
        member.adjustSchool(school);
        return school.getId();
    }
}
