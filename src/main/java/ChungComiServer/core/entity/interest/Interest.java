package ChungComiServer.core.entity.interest;

import ChungComiServer.core.entity.Comment;
import ChungComiServer.core.entity.MemberCompany;
import jakarta.persistence.*;
import lombok.Getter;

import javax.lang.model.element.Name;
import java.util.List;

@Entity
@Getter
@Table(name = "INTEREST")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Interest {

    @Id @GeneratedValue
    @Column(name = "INTEREST_ID")
    private Long id;

    @Column(name = "INTEREST_NAME")
    private String name;

    @Column(name = "INTEREST_RATE")
    private Integer rate;

}
