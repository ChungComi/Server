package ChungComiServer.dot.core.entity.interest;

import ChungComiServer.dot.core.entity.MemberTechStack;
import ChungComiServer.dot.core.entity.Comment;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "TECHSTACK")
public class TechStack extends Interest{

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "techStack" )
    List<MemberTechStack> memberTechStacks = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "techStack", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Comment> comments = new ArrayList<>();
}
