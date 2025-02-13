package ChungComiServer.dot.core.service;

import ChungComiServer.dot.core.dto.post.ResponsePostDTO;
import ChungComiServer.dot.core.entity.Member;
import ChungComiServer.dot.core.entity.Post;
import ChungComiServer.dot.core.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

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

    public List<ResponsePostDTO> findByPageNum(String stringPageNum){
        Integer firstPostNum = getFirstPostNum(stringPageNum);
        List<Post> posts = postRepository.findByFirstPostNum(firstPostNum);
        if(posts.isEmpty())
            throw new NoSuchElementException("게시물이 존재하지 않습니다.");
        return posts.stream().map(ResponsePostDTO::new).toList();
    }

    public Integer findAllPostsNum(){
        return postRepository.findALlPostsNum();
    }

    @Transactional(readOnly = false)
    public Long registerPost(Long userId, String title, String content) throws InvalidPropertiesFormatException {
        Member member = memberRepository.findById(userId);
        Post post = new Post(title,content);
        post.addMemberRelationship(member);
        return postRepository.registerPost(post);
    }

    @Transactional(readOnly = false)
    public ResponsePostDTO modifyPost(Long userId, String stringPostId, String title, String content) throws InvalidPropertiesFormatException, IllegalAccessException {
        Long postId = Long.valueOf(stringPostId);
        Post post = postRepository.findById(postId);
        post.modifyPost(userId,title,content);
        return new ResponsePostDTO(post);
    }

    @Transactional(readOnly = false)
    public void deletePost(Long userId, String stringPostId) throws IllegalAccessException {
        Long postId = Long.valueOf(stringPostId);
        Post post = postRepository.findById(postId);
        if(post.getMember().getId().equals(userId))
            postRepository.deletePost(postId);
        else throw new IllegalAccessException("게시물 작성자만 삭제가 가능합니다.");
    }

    private static Integer getFirstPostNum(String stringPageNum) {
        int pageNum = Integer.parseInt(stringPageNum);
        return (pageNum-1)*10;
    }

}
