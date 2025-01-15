package ChungComiServer.dot.core.dto.company;

import ChungComiServer.dot.core.entity.Comment;
import ChungComiServer.dot.core.entity.interest.Company;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Getter
public class ResponseCompanyDTO {

    private String name;
    private Long rate;
    private List<CommentDTO> comments = new ArrayList<>();


    public ResponseCompanyDTO(Company company){
        this.name = company.getName();
        this.rate = company.getRate();
        this.comments = company.getComments().stream().map(CommentDTO::new).toList();
    }
}
