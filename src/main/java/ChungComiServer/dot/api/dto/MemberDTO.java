package ChungComiServer.dot.api.dto;

import ChungComiServer.dot.core.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {

    private Long id;
    private String name;

    public MemberDTO(Member member){
        this.id = member.getId();
        this.name = member.getName();
    }
}
