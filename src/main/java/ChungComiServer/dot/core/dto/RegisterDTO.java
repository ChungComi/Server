package ChungComiServer.dot.core.dto;

import ChungComiServer.dot.core.entity.MemberCompany;
import ChungComiServer.dot.core.entity.MemberTechStack;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RegisterDTO {

    public RegisterDTO(){}

    @NotEmpty(message = "name must be filled")
    String name;
    @NotEmpty(message = "id must be filled")
    String loginId;
    @NotEmpty(message = "pw must be filled")
    String loginPw;
    @NotEmpty(message = "interested companies must be filled")
    List<MemberCompany> memberCompanies;
    @NotEmpty(message = "interested tech stacks must be filled")
    List<MemberTechStack> memberTechStacks;
}
