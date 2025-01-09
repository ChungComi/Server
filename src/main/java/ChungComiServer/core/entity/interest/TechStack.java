package ChungComiServer.core.entity.interest;

import ChungComiServer.core.entity.Comment;
import ChungComiServer.core.entity.MemberCompany;
import ChungComiServer.core.entity.MemberTechStack;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "TECHSTACK")
public class TechStack extends Interest{

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "techStack" )
    List<MemberTechStack> memberTechStacks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "techStack")
    List<Comment> comments;
}
