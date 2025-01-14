package ChungComiServer.dot.core.dto.api;

import lombok.Getter;

@Getter
public class GetCommentDetailDTO {
    private String content;
    private Long likes;

    public GetCommentDetailDTO(String content,Long likes){
        this.content = content;
        this.likes = likes;
    }
}
