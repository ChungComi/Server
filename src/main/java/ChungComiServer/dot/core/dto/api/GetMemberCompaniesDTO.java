package ChungComiServer.dot.core.dto.api;

import ChungComiServer.dot.core.dto.MemberCompanyDTO;
import ChungComiServer.dot.core.entity.Comment;
import ChungComiServer.dot.core.entity.Member;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetMemberCompaniesDTO {

    private String myName;
    private List<GetCompanyDetailDTO> memberCompanies = new ArrayList<>();

    public GetMemberCompaniesDTO(Member member){
        this.myName = member.getName();

        /** 반환할 선호 기업 DTO 설정 **/
        if(!member.getMemberCompanies().isEmpty()){
            this.memberCompanies = member.getMemberCompanies()
                    .stream().map(memberCompany -> new GetCompanyDetailDTO(memberCompany.getCompany().getName(),memberCompany.getCompany().getRate(),memberCompany.getCompany().getComments()))
                    .toList();
        }
    }
}
