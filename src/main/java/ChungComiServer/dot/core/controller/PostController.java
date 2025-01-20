package ChungComiServer.dot.core.controller;

import ChungComiServer.dot.core.dto.auth.RegisterDTO;
import ChungComiServer.dot.core.dto.post.ModifyPostDTO;
import ChungComiServer.dot.core.dto.post.RegisterPostDTO;
import ChungComiServer.dot.core.dto.post.ResponsePostDTO;
import ChungComiServer.dot.core.service.PostService;
import ChungComiServer.dot.global.response.ErrorCode;
import ChungComiServer.dot.global.response.Response;
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

    @GetMapping("")
    public Response getAllPosts(){
        try{
            List<ResponsePostDTO> postsDTOs = postService.findAll();
            return Response.success(postsDTOs);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @GetMapping("/{postId}")
    public Response getPostById(@PathVariable("postId") String postId){
        try{
            ResponsePostDTO postDTO = postService.findById(postId);
            return Response.success(postDTO);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @GetMapping("/title/{postTitle}")
    public Response getPostByTitle(@PathVariable("postTitle") String postTitle){
        try{
            List<ResponsePostDTO> postDTOs = postService.findByTitle(postTitle);
            return Response.success(postDTOs);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @PostMapping("")
    public Response registerPost(@Valid @RequestBody RegisterPostDTO registerPostDTO, BindingResult result){
        try{
            if(result.hasErrors())
                return Response.failure(new ErrorCode(result.getFieldError().toString()));
            Long postId = postService.registerPost(registerPostDTO.getTitle(), registerPostDTO.getContent());
            return Response.success(postId);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @PutMapping("/{postId}")
    public Response modifyPost(@PathVariable("postId") String postId, @RequestBody ModifyPostDTO modifyPostDTO){
        try{
            ResponsePostDTO responsePostDTO = postService.modifyPost(postId, modifyPostDTO.getTitle(), modifyPostDTO.getContent());
            return Response.success(responsePostDTO);
        } catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @DeleteMapping("/{postId}")
    public Response deletePost(@PathVariable("postId") String postId){
        try{
            postService.deletePost(postId);
            return Response.success();
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

}
