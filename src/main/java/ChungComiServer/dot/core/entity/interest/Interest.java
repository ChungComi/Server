package ChungComiServer.dot.core.entity.interest;

import jakarta.persistence.*;
import lombok.Getter;

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
