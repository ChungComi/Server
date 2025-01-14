package ChungComiServer.dot.api.service;

import ChungComiServer.dot.api.dto.GetMemberCompaniesDTO;
import ChungComiServer.dot.core.entity.Member;
import ChungComiServer.dot.api.repository.MemberApiRepository;
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

    public GetMemberCompaniesDTO findMemberCompaniesById(String memberId) {
        Member memberCompanies = memberApiRepository.findMemberCompanies(memberId);
        if(memberCompanies== null)
            throw new NoSuchElementException("멤버가 존재하지 않음");
        return new GetMemberCompaniesDTO(memberCompanies);

    }
}
