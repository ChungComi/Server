package ChungComiServer.dot.core.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberTechStackDTO {
    private Long preference;
    private String techStackName;

    public MemberTechStackDTO(){}

    public MemberTechStackDTO(Long preference, String techStackName){
        this.preference = preference;
        this.techStackName = techStackName;
    }
}
