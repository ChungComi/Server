package ChungComiServer.dot.api.repository;

import ChungComiServer.dot.core.entity.Comment;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
@Slf4j
public class CommentAPIRepository {
    
    private final EntityManager em;

    public Comment findById(Long commentId) {
        return em.createQuery("select c from Comment c where c.id =: commentId", Comment.class)
                .setParameter("commentId",commentId)
                .getSingleResult();
    }
}
