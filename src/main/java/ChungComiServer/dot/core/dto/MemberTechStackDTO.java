package ChungComiServer.dot.core.dto;

import lombok.Getter;

@Getter
public class MemberTechStackDTO {
    private Long preference;
    private String techStackName;

    public MemberTechStackDTO(){}

    public MemberTechStackDTO(Long preference, String techStackName){
        this.preference = preference;
        this.techStackName = techStackName;
    }
}
