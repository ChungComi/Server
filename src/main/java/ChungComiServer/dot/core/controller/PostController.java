package ChungComiServer.dot.core.controller;

import ChungComiServer.dot.core.dto.post.ResponsePostDTO;
import ChungComiServer.dot.core.service.PostService;
import ChungComiServer.dot.global.response.ErrorCode;
import ChungComiServer.dot.global.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping("")
    public Response getAllPosts(){
        try{
            List<ResponsePostDTO> postsDTOs = postService.findAll();
            return Response.success(postsDTOs);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }
}
