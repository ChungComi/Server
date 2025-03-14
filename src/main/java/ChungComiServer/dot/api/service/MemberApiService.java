package ChungComiServer.dot.api.service;

import ChungComiServer.dot.api.repository.MemberApiRepository;
import ChungComiServer.dot.core.entity.Member;
import ChungComiServer.dot.core.entity.MemberCompany;
import ChungComiServer.dot.core.entity.MemberTechStack;
import ChungComiServer.dot.core.entity.School;
import ChungComiServer.dot.core.entity.interest.Company;
import ChungComiServer.dot.core.entity.interest.TechStack;
import ChungComiServer.dot.core.repository.CompanyRepository;
import ChungComiServer.dot.core.repository.MemberRepository;
import ChungComiServer.dot.core.repository.SchoolRepository;
import ChungComiServer.dot.core.repository.TechStackRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
@Slf4j
public class MemberApiService {

    private final MemberApiRepository memberApiRepository;
    private final MemberRepository memberRepository;
    private final SchoolRepository schoolRepository;
    private final CompanyRepository companyRepository;
    private final TechStackRepository techStackRepository;

    @Transactional
    public Long registerSchool(Long userId, String name) {
        School school = new School(name);
        Long schoolId = schoolRepository.save(school);
        Member member = memberRepository.findById(userId);
        member.adjustSchool(school);
        return schoolId;
    }

    @Transactional
    public Long setMemberCompaniesByName(Long userId, String companyName) {
        Member member = memberRepository.findById(userId);
        List<Company> companies = companyRepository.findByName(companyName);
        MemberCompany memberCompany = createMemberInterest(member, companies);
        return memberApiRepository.saveMemberInterest(memberCompany);
    }

    @Transactional
    public Long setMemberTechStackByName(Long userId, String techStackName) {
        Member member = memberRepository.findById(userId);
        List<TechStack> techStacks = techStackRepository.findByName(techStackName);
        MemberTechStack memberTechStack = createMemberTechStack(member, techStacks);
        return memberApiRepository.saveMemberInterest(memberTechStack);
    }

    @Transactional
    public void deleteMemberCompanyByName(Long userId, String companyName) {
        MemberCompany memberCompany = memberApiRepository.findMemberCompanyByCompanyName(userId,companyName);
        memberApiRepository.deleteMemberCompany(memberCompany);
    }

    @Transactional
    public void deleteMemberTechStackByName(Long userId, String techStackName){
        MemberTechStack techStack = memberApiRepository.findMemberTechStackByName(userId,techStackName);
        memberApiRepository.deleteMemberTechStackByName(techStack);
    }


    private MemberCompany createMemberInterest(Member member, List<Company> companies) {
        MemberCompany memberCompany = new MemberCompany();
        if (companies.size() != 1)
            throw new NoSuchElementException("같은 이름을 가진 회사가 2개 이상 존재하거나 존재하지 않는 회사입니다.");
        return memberCompany.adjustInterestCompanyInMember(member, companies.get(0));
    }

    private MemberTechStack createMemberTechStack(Member member, List<TechStack> techStacks) {
        MemberTechStack memberTechStack = new MemberTechStack();
        if (techStacks.size() != 1)
            throw new NoSuchElementException("같은 이름을 가진 기술스택이 2개 이상 존재하거나 존재하지 않는 스택입니다.");
        return memberTechStack.adjustInterestTechStackMember(member, techStacks.get(0));
    }
}
