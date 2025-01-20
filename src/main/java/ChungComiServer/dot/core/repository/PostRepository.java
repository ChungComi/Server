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
        return em.createQuery("select p from Post p left join fetch p.comments", Post.class)
                .getResultList();
    }

    public Post findById(Long postId) {
        return em.find(Post.class,postId);
    }

    public List<Post> findByTitle(String postTitle) {
        return em.createQuery("select p from Post p left join fetch p.comments where p.title like :postTitle", Post.class)
                .setParameter("postTitle",postTitle)
                .getResultList();
    }

    public Long registerPost(Post post) {
        em.persist(post);
        return post.getId();
    }

    public void deletePost(Long postId) {
        em.createQuery("delete from Post p where p.id =: postId")
                .setParameter("postId",postId)
                .executeUpdate();
        em.clear();
    }
}
