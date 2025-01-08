package ChungComiServer.core.entity.interest;

import ChungComiServer.core.entity.Comment;
import ChungComiServer.core.entity.MemberCompany;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "COMPANY")
public class Company extends Interest{

    @OneToMany(fetch = FetchType.LAZY)
    private List<MemberCompany> memberCompanies;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> comments;
}
