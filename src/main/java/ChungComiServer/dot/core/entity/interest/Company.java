package ChungComiServer.dot.core.entity.interest;

import ChungComiServer.dot.core.entity.Comment;
import ChungComiServer.dot.core.entity.MemberCompany;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "COMPANY")
public class Company extends Interest{

    @BatchSize(size = 10)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private List<MemberCompany> memberCompanies = new ArrayList<>();

    @BatchSize(size = 20)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    /** 기본 생성자 메서드 **/
    public Company(){}

    /** 기업 등록 생성자 메서드 **/
    @Builder
    public Company(String companyName, String description){
        this.name = companyName;
        this.description = description;
    }

}
