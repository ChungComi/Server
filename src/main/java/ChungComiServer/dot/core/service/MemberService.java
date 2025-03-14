package ChungComiServer.dot.core.service;

import ChungComiServer.dot.core.dto.member.ResponseMemberDTO;
import ChungComiServer.dot.core.entity.Member;
import ChungComiServer.dot.core.entity.MemberCompany;
import ChungComiServer.dot.core.entity.MemberTechStack;
import ChungComiServer.dot.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public List<ResponseMemberDTO> findAll() {
        List<Member> all = memberRepository.findAll();
        if(all.isEmpty())
            throw new NoSuchElementException("회원 존재 x");
        return all.stream().map(ResponseMemberDTO::new).toList();
    }

    public ResponseMemberDTO findById(String stringMemberId) {
        Long memberId = Long.valueOf(stringMemberId);
        Member foundMember = memberRepository.findById(memberId);
        if(foundMember == null)
            throw new NoSuchElementException("회원 존재 x");
        return new ResponseMemberDTO(foundMember);
    }

    public ResponseMemberDTO findById(Long memberId) {
        List<MemberCompany> memberCompanies = memberRepository.findMemberCompanyByMemberId(memberId);
        List<MemberTechStack> memberTechStacks = memberRepository.findMemberTechStackByMemberId(memberId);
        return new ResponseMemberDTO(memberCompanies,memberTechStacks);
    }

    public List<ResponseMemberDTO> findByName(String memberName) {
        List<Member> members = memberRepository.findByName(memberName);
        if(members.isEmpty())
            throw new NoSuchElementException("회원 존재 x");
        return members.stream().map(ResponseMemberDTO::new).toList();
    }
}
