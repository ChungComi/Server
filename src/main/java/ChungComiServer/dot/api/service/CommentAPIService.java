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
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CommentAPIService {

    private final MemberRepository memberRepository;
    private final CommentAPIRepository commentRepository;

    public void registerChildComment(Long userId, Long commentId, String content) {
        Member member = memberRepository.findById(userId);
        Comment comment = commentRepository.findById(commentId);
        Comment childComment = new Comment(content);
        childComment.addMember(member);
        comment.addChildComment(childComment);
    }

}
