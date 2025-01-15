package ChungComiServer.dot.core.repository;

import ChungComiServer.dot.core.entity.Post;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PostRepository {

    private final EntityManager em;

    public List<Post> findAll() {
        return em.createQuery("select p from Post p join fetch p.comments", Post.class)
                .getResultList();
    }

}
