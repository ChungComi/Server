package ChungComiServer.dot.core.entity.interest;

import ChungComiServer.dot.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "INTEREST")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Interest extends BaseEntity {

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
    public void modifyInfo(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
