package ChungComiServer.dot.core.post;

import ChungComiServer.dot.core.ServiceTest;
import ChungComiServer.dot.core.dto.post.ResponsePostDTO;
import ChungComiServer.dot.core.entity.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

@SpringBootTest
@Transactional
public class PostServiceTest extends ServiceTest {

    @Nested
    class 게시물_등록_조회는{

        @MethodSource("generateArguments")
        @ParameterizedTest
        public void 제대로_게시물_등록_및_모든_게시물_조회(String title,String content) throws Exception {
            //given
            List<Post> posts = List.of(
                    new Post(title, content),
                    new Post(title+"2", content+"2"),
                    new Post(title+"3", content+"3")
            );


            //when
            List<Long> postIds = new ArrayList<>();
            for (Post post : posts) {
                 postIds.add(postService.registerPost(null,post.getTitle(), post.getContent()));
            }


            //then
            Assertions.assertThat(postService.findAll())
                    .extracting(ResponsePostDTO::getTitle)
                    .containsExactlyInAnyOrder(posts.get(0).getTitle(),posts.get(1).getTitle(),posts.get(2).getTitle());

            Assertions.assertThat(postService.findAll())
                    .extracting(ResponsePostDTO::getContent)
                    .containsExactlyInAnyOrder(posts.get(0).getContent(),posts.get(1).getContent(),posts.get(2).getContent());

        }

        @ParameterizedTest
        @MethodSource("generateInvalidArguments")
        public void 잘못된_게시물_등록(String title,String content) throws Exception{
            //expect
            org.junit.jupiter.api.Assertions.assertThrows(InvalidPropertiesFormatException.class,()->
                    postService.registerPost(null,title,content));
            //then
        }

        @ParameterizedTest
        @MethodSource("generateArguments")
        public void 아이디로_게시물_조회(String title,String content) throws Exception {
            //given

            //when
            Long registeredId = postService.registerPost(null,title, content);
            //then
            Assertions.assertThat(postService.findById(String.valueOf(registeredId)))
                    .isExactlyInstanceOf(ResponsePostDTO.class);

            Assertions.assertThat(postService.findById(String.valueOf(registeredId)))
                    .usingRecursiveComparison()
                    .isEqualTo(new ResponsePostDTO(new Post(title,content)));
        }

        @ParameterizedTest
        @MethodSource("generateArguments")
        public void 제목으로_게시물_조회(String title,String content) throws Exception {
            //given
            List<Post> posts = List.of(
                    new Post(title, content),
                    new Post(title, content+"2"),
                    new Post(title, content+"3")
            );

            //when
            for (Post post : posts) {
                postService.registerPost(null,post.getTitle(),post.getContent());
            }

            //then
            Assertions.assertThat(postService.findByTitle(title)).extracting(ResponsePostDTO::getContent)
                    .containsExactlyInAnyOrder(posts.stream().map(Post::getContent).toArray(String[]::new));
        }

        private static Stream<Arguments> generateArguments(){
            return Stream.of(
                    Arguments.of("title","content"),
                    Arguments.of("title2","content2"),
                    Arguments.of("title3","content3")
            );
        }

        private static Stream<Arguments> generateInvalidArguments(){
            return Stream.of(
                    Arguments.of(null,"content"),
                    Arguments.of("title2",null),
                    Arguments.of(null,null)
            );
        }
    }

    @Nested
    class 게시물_수정_삭제는{
        @ParameterizedTest
        @MethodSource("generateArguments")
        public void 제대로된_게시물_수정(String title,String content) throws InvalidPropertiesFormatException, IllegalAccessException {
            //given
            Post post = new Post("abc","def");
            //when
            Long registeredId = postService.registerPost(null,post.getTitle(), post.getContent());

            //then
            Assertions.assertThat(postService.modifyPost(null, String.valueOf(registeredId),title,content))
                    .usingRecursiveComparison()
                    .isEqualTo(new ResponsePostDTO((new Post(title,content))));
        }

        @ParameterizedTest
        @MethodSource("generateInvalidArguments")
        public void 잘못된_게시물_수정(String title, String content) throws Exception {
            //given
            Post post = new Post("abc","def");

            //when
            Long registeredId = postService.registerPost(null,post.getTitle(), post.getContent());

            //Expect
            org.junit.jupiter.api.Assertions.assertThrows(InvalidPropertiesFormatException.class,()->
                    postService.modifyPost(null, String.valueOf(registeredId),title,content));
        }

        @Test
        public void 게시물_삭제() throws Exception {
            //given
            Post post = new Post("abc","def");
            Long registeredId = postService.registerPost(null,post.getTitle(), post.getContent());

            //when
            postService.deletePost(null,String.valueOf(registeredId));

            //expect
            org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class,() ->
                            postService.findById(String.valueOf(registeredId)));
        }

        private static Stream<Arguments> generateArguments(){
            return Stream.of(
                    Arguments.of("title","content"),
                    Arguments.of("title2","content2"),
                    Arguments.of("title3","content3")
            );
        }

        private static Stream<Arguments> generateInvalidArguments(){
            return Stream.of(
                    Arguments.of(null,"content"),
                    Arguments.of("title2",null),
                    Arguments.of(null,null)
            );
        }
    }


}
