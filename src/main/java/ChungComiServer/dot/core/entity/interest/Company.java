package ChungComiServer.dot.core.entity.interest;

import ChungComiServer.dot.core.entity.Comment;
import ChungComiServer.dot.core.entity.MemberCompany;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "COMPANY")
public class Company extends Interest{

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private List<MemberCompany> memberCompanies = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    /** 기본 생성자 메서드 **/
    public Company(){}

    /** 기업 등록 생성자 메서드 **/
    public Company(String companyName){
        this.name = companyName;
    }
}
