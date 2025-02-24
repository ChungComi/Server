package ChungComiServer.dot.api.service;

import ChungComiServer.dot.api.repository.CommentAPIRepository;
import ChungComiServer.dot.core.entity.Comment;
import ChungComiServer.dot.core.entity.Member;
import ChungComiServer.dot.core.repository.MemberRepository;
import ChungComiServer.dot.core.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CommentAPIService {

    private final MemberRepository memberRepository;
    private final CommentAPIRepository commentRepository;

    @Transactional
    public void registerChildComment(Long userId, Long commentId, String content) {
        Member member = memberRepository.findById(userId);
        Comment comment = commentRepository.findById(commentId);
        Comment childComment = new Comment(content);
        childComment.addMember(member);
        comment.addChildComment(childComment);
    }

    @Transactional
    public void likeComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId);
        comment.plusLike();
    }

    @Transactional
    public void deleteComment(Long userId, Long commentId) throws IllegalAccessException {
        Comment comment = commentRepository.findById(commentId);
        comment.verifyAuthorization(userId);
        commentRepository.deleteComment(commentId);
    }

    @Transactional
    public Comment modifyComment(Long userId, Long commentId, String content) throws IllegalAccessException {
        Comment comment = commentRepository.findById(commentId);
        comment.verifyAuthorization(userId);
        comment.modifyContent(content);
        return comment;
    }
}
