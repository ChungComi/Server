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
}
