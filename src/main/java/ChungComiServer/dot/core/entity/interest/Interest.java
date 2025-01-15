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
    protected String name;

    @Column(name = "INTEREST_RATE")
    protected Long rate;

    @Column(name = "DESCRIPTION")
    protected String description;

    /** 정보 수정 메서드 **/
    public void modifyCompanyInfo(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
