package ChungComiServer.dot.core.dto;

import ChungComiServer.dot.core.entity.MemberCompany;
import ChungComiServer.dot.core.entity.MemberTechStack;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RegisterDTO {
    String name;
    String loginId;
    String loginPw;
    java.util.List<MemberCompany> memberCompanies;
    List<MemberTechStack> memberTechStacks;
}
