package ChungComiServer.dot.core.post;

import ChungComiServer.dot.core.controller.PostController;
import ChungComiServer.dot.core.dto.post.ResponsePostDTO;
import ChungComiServer.dot.core.entity.Post;
import ChungComiServer.dot.core.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PostController.class)
@AutoConfigureRestDocs(outputDir = "build/snippets/post")
@AutoConfigureMockMvc(addFilters = false) // 시큐리티 필터 비활성화
public class PostControllerRestDocsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PostService postService;

    @Test
    public void 모든_게시물을_조회한다() throws Exception {
        //given
        List<ResponsePostDTO> posts = List.of(
                new ResponsePostDTO(new Post("title", "content")),
                new ResponsePostDTO(new Post("title2", "content2"))
        );
        when(postService.findAll()).thenReturn(posts);
        //when
        this.mockMvc.perform(get("/post"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("title")))
        //then
                .andDo(document("all"));
    }

    @Test
    public void 아이디를_통해_특정_게시물_조회() throws Exception{
        //given
        ResponsePostDTO post = new ResponsePostDTO(new Post("title","content"));
        when(postService.findById("1")).thenReturn(post);
        //when
        this.mockMvc.perform(get("/post/{postId}",1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("title")))
        //then
                .andDo(document("byId"));
    }

    @Test
    public void 제목을_통해_특정_게시물_조회() throws Exception{
        //given
        List<ResponsePostDTO> posts = List.of(
                new ResponsePostDTO(new Post("title2", "content")),
                new ResponsePostDTO(new Post("title2", "content2")),
                new ResponsePostDTO(new Post("title2", "content3"))
        );
        when(postService.findByTitle("title2")).thenReturn(posts);
        //when
        this.mockMvc.perform(get("/post/title/{postTitle}","title2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("content")))
                .andExpect(content().string(containsString("content2")))
                .andExpect(content().string(containsString("content3")))
        //then
                .andDo(document("byTitle"));
    }

    @Test
    /**
     * JSON Path $: 루트 객체를 가리킵니다
     */
    public void 게시물_작성() throws Exception{
        //given
        ResponsePostDTO post = new ResponsePostDTO(new Post("title","content"));
        when(postService.registerPost(null,"title","content")).thenReturn(1L);
        //when
        this.mockMvc.perform(post("/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(post)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.result.data").value(1L))
        //then
                .andDo(document("register"));
    }

    @Test
    public void 게시물_수정() throws Exception {
        //given
        ResponsePostDTO post = new ResponsePostDTO(Post.builder().title("title2").content("content2").build());
        when(postService.modifyPost(null, "1","title2","content2"))
                .thenReturn(post);
        //when
        this.mockMvc.perform(put("/post/{postId}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(post)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.result.data.title").value("title2"))
                .andExpect(jsonPath("$.result.data.content").value("content2"))
        //then
                .andDo(document("modify"));
    }

    @Test
    public void 게시물_삭제() throws Exception {
        //given
        String postId = "1";
        doNothing().when(postService).deletePost(null,postId);

        //when
        this.mockMvc.perform(delete("/post/{postId}",postId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
        //then
                .andDo(document("delete"));

    }

}
