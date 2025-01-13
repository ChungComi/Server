package ChungComiServer.dot.core.dto;

import ChungComiServer.dot.core.entity.interest.Company;
import lombok.Getter;

@Getter
public class MemberCompanyDTO {
    private Long preference;
    private String companyName;

    MemberCompanyDTO(){    }

    public MemberCompanyDTO(Long preference, String companyName){
        this.preference = preference;
        this.companyName = companyName;
    }
}
