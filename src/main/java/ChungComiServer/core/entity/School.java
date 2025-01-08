package ChungComiServer.core.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "SCHOOL")
public class School {

    @Id @GeneratedValue
    @Column(name = "SCHOOL_ID")
    private Long id;

    @Column(name = "SCHOOL_NAME")
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Member> members;
}
