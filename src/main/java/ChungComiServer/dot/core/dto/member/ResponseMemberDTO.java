package ChungComiServer.dot.core.dto.member;

import ChungComiServer.dot.core.entity.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResponseMemberDTO {

    private String name;
    private List<MemberCompanyDTO> memberCompanies = new ArrayList<>();
    private List<MemberTechStackDTO> memberTechStacks = new ArrayList<>();
    private SchoolDTO school;

    public ResponseMemberDTO(){}

    public ResponseMemberDTO(Member member){

        /** 반환할 이름 DTO 설정 **/
        this.name = member.getName();

        /** 반환할 선호 기업 DTO 설정 **/
        if(!member.getMemberCompanies().isEmpty()){
            this.memberCompanies = member.getMemberCompanies()
                    .stream().map(memberCompany -> new MemberCompanyDTO(memberCompany.getPreference(),memberCompany.getCompany().getName()))
                    .toList();
        }

        /** 반환할 선호 기술스택 DTO 설정 **/
        if(!member.getMemberCompanies().isEmpty()){
            this.memberTechStacks = member.getMemberTechStacks()
                    .stream().map(memberTechStack -> new MemberTechStackDTO(memberTechStack.getPreference(),memberTechStack.getTechStack().getName()))
                    .toList();
        }

        /** 반환할 학교 DTO 설정 **/
        this.school = new SchoolDTO(member.getSchool().getName());
    }
}
