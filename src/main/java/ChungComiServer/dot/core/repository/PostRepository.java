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
    private static final Integer MAX_RESULT = 10;

    public List<Post> findAll() {
        return em.createQuery("select p from Post p left join fetch p.member", Post.class)
                .getResultList();
    }

    public Long findAllPostsNum(){
        return em.createQuery("select count(p) from Post p", Long.class)
                .getSingleResult();
    }

    public Post findById(Long postId) {
        return em.createQuery("select p from Post p left join fetch p.member where p.id =:postId",Post.class)
                .setParameter("postId",postId)
                .getSingleResult();
    }

    public List<Post> findByTitle(String postTitle) {
        return em.createQuery("select p from Post p left join fetch p.member where p.title like :postTitle", Post.class)
                .setParameter("postTitle",postTitle)
                .getResultList();
    }

    public List<Post> findByMemberName(String memberName) {
        return em.createQuery("select p from Post p join fetch p.member where p.member.name like :memberName", Post.class)
                .setParameter("memberName",memberName)
                .getResultList();
    }

    public List<Post> findByFirstPostNum(Integer firstPost){
        return em.createQuery("select p from Post p join fetch p.member",Post.class)
                .setFirstResult(firstPost)
                .setMaxResults(PostRepository.MAX_RESULT)
                .getResultList();
    }

    public Long registerPost(Post post) {
        em.persist(post);
        return post.getId();
    }

    public void deletePost(Post post) {
        em.remove(post);
    }
}
