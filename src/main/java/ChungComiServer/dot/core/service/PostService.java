package ChungComiServer.dot.core.service;

import ChungComiServer.dot.core.dto.post.ResponsePostDTO;
import ChungComiServer.dot.core.entity.Post;
import ChungComiServer.dot.core.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<ResponsePostDTO> findAll() {
        List<Post> posts = postRepository.findAll();
        if(posts.isEmpty())
            throw new NoSuchElementException("게시물이 존재하지 않습니다.");
        return posts.stream().map(ResponsePostDTO::new).toList();
    }
}
