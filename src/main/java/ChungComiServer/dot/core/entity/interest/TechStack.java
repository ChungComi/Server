package ChungComiServer.dot.core.entity.interest;

import ChungComiServer.dot.core.entity.MemberTechStack;
import ChungComiServer.dot.core.entity.Comment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "TECHSTACK")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class TechStack extends Interest{

    @BatchSize(size = 10)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "techStack" )
    List<MemberTechStack> memberTechStacks = new ArrayList<>();

    @BatchSize(size = 20)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "techStack", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Comment> comments = new ArrayList<>();

    /** 테스트를 위한 기술스택 값 주입 메서드 **/
    public TechStack (String name, String description){
        this.name= name;
        this.description = description;
    }
}
