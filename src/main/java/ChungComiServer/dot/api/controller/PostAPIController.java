package ChungComiServer.dot.api.controller;

import ChungComiServer.dot.core.dto.post.ResponsePostDTO;
import ChungComiServer.dot.core.service.PostService;
import ChungComiServer.dot.global.response.ErrorCode;
import ChungComiServer.dot.global.response.Response;
import ChungComiServer.dot.global.security.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostAPIController {

    private final PostService postService;
    private final UserContext userContext;

    @GetMapping("/{pageNum}")
    public Response getPostsByPageNumb(@PathVariable("pageNum")String pageNum){
        try{
            List<ResponsePostDTO> postsDTOs = postService.findByPageNum(pageNum);
            return Response.success(postsDTOs);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @GetMapping("")
    public Response getAllPostsNum(){
        try{
            Integer postsNum = postService.findAllPostsNum();
            return Response.success(postsNum);
        } catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }
}
