package ChungComiServer.dot.core.entity;

import ChungComiServer.dot.global.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "SCHOOL")
public class School extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "SCHOOL_ID")
    private Long id;

    @Column(name = "SCHOOL_NAME")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Member> members = new ArrayList<>();

    protected School(){}


    /* 회원 학교 설정 시 학교 객체 생성에 사용하는 함수 */
    public School(String name){
        this.name = name;
    }
}
