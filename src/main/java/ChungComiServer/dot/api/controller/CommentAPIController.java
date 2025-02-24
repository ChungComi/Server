package ChungComiServer.dot.api.controller;

import ChungComiServer.dot.api.service.CommentAPIService;
import ChungComiServer.dot.core.service.PostService;
import ChungComiServer.dot.global.response.ErrorCode;
import ChungComiServer.dot.global.response.Response;
import ChungComiServer.dot.global.security.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentAPIController {

    private final UserContext userContext;
    private final PostService postService;
    private final CommentAPIService commentService;

    @PostMapping("/{postId}")
    public Response registerComments(@PathVariable Long postId, @RequestBody String content) {
        try {
            postService.registerComment(userContext.getUserId(), postId, content);
            return Response.success();
        } catch (Exception e) {
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @PostMapping("/child/{commentId}")
    public Response registerChildComments(@PathVariable Long commentId, @RequestBody String content) {
        try {
            commentService.registerChildComment(userContext.getUserId(), commentId, content);
            return Response.success();
        } catch (Exception e) {
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @PostMapping("/like/{commentId}")
    public Response likeComment(@PathVariable Long commentId){
        try {
            commentService.likeComment(commentId);
            return Response.success();
        } catch (Exception e) {
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @DeleteMapping("/{commentId}")
    public Response deleteComment(@PathVariable Long commentId){
        try {
            commentService.deleteComment(userContext.getUserId(),commentId);
            return Response.success();
        } catch (Exception e) {
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

}
