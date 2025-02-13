package ChungComiServer.dot.core.controller;

import ChungComiServer.dot.api.dto.ResponsePostDTOForBoard;
import ChungComiServer.dot.core.dto.auth.RegisterDTO;
import ChungComiServer.dot.core.dto.post.ModifyPostDTO;
import ChungComiServer.dot.core.dto.post.RegisterPostDTO;
import ChungComiServer.dot.core.dto.post.ResponsePostDTO;
import ChungComiServer.dot.core.service.PostService;
import ChungComiServer.dot.global.response.ErrorCode;
import ChungComiServer.dot.global.response.Response;
import ChungComiServer.dot.global.security.UserContext;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final UserContext userContext;

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
