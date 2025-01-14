package ChungComiServer.dot.api.dto;

import ChungComiServer.dot.core.entity.Comment;
import lombok.Getter;

import java.util.List;

@Getter
public class GetCompanyDetailDTO {
    private String companyName;
    private Long rate;
    private List<GetCommentDetailDTO> comments;

    public GetCompanyDetailDTO(String companyName, Long rate, List<Comment>comments){
        this.companyName = companyName;
        this.rate = rate;
        if(!comments.isEmpty()){
            this.comments = comments.stream().map(comment -> new GetCommentDetailDTO(comment.getContent(),comment.getLikes())).toList();
        }
    }
}
