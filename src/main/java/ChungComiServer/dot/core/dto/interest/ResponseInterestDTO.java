package ChungComiServer.dot.core.dto.interest;

import ChungComiServer.dot.core.entity.interest.Company;
import ChungComiServer.dot.core.entity.interest.TechStack;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResponseInterestDTO {

    private String name;
    private Long rate;
    private List<CommentDTO> comments = new ArrayList<>();


    public ResponseInterestDTO(Company company){
        this.name = company.getName();
        this.rate = company.getRate();
        this.comments = company.getComments().stream().map(CommentDTO::new).toList();
    }

    public ResponseInterestDTO(TechStack techStack){
        this.name = techStack.getName();
        this.rate = techStack.getRate();
        this.comments = techStack.getComments().stream().map(CommentDTO::new).toList();
    }


}
