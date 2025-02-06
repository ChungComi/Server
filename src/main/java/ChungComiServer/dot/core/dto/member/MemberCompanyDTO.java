package ChungComiServer.dot.core.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCompanyDTO {
    private Long preference;
    private String companyName;

    MemberCompanyDTO(){    }

    public MemberCompanyDTO(Long preference, String companyName){
        this.preference = preference;
        this.companyName = companyName;
    }
}
