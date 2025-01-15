package ChungComiServer.dot.core.dto.member;

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
