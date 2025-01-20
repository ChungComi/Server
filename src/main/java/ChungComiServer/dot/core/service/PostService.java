package ChungComiServer.dot.core.service;

import ChungComiServer.dot.core.dto.post.ResponsePostDTO;
import ChungComiServer.dot.core.entity.Post;
import ChungComiServer.dot.core.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<ResponsePostDTO> findAll() {
        List<Post> posts = postRepository.findAll();
        if(posts.isEmpty())
            throw new NoSuchElementException("게시물이 존재하지 않습니다.");
        return posts.stream().map(ResponsePostDTO::new).toList();
    }

    public ResponsePostDTO findById(String stringPostId) {
        Long postId = Long.valueOf(stringPostId);
        Post post = postRepository.findById(postId);
        if(post == null){
            throw new NoSuchElementException("해당 게시물이 존재하지 않습니다.");
        }
        return new ResponsePostDTO(post);
    }

    public List<ResponsePostDTO> findByTitle(String postTitle) {
        List<Post> posts = postRepository.findByTitle(postTitle);
        if(posts.isEmpty())
            throw new NoSuchElementException("해당 게시물이 존재하지 않습니다.");
        return posts.stream().map(ResponsePostDTO::new).toList();
    }

    @Transactional(readOnly = false)
    public Long registerPost(String title, String content) throws InvalidPropertiesFormatException {
        Post post = new Post(title,content);
        return postRepository.registerPost(post);
    }

    @Transactional(readOnly = false)
    public ResponsePostDTO modifyPost(String stringPostId, String title, String content) throws InvalidPropertiesFormatException {
        Long postId = Long.valueOf(stringPostId);
        Post post = postRepository.findById(postId);
        post.modifyPost(title,content);
        return new ResponsePostDTO(post);
    }

    @Transactional(readOnly = false)
    public void deletePost(String stringPostId) {
        Long postId = Long.valueOf(stringPostId);
        postRepository.deletePost(postId);
    }
}
