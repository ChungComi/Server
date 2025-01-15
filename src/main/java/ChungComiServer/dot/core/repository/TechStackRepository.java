package ChungComiServer.dot.core.repository;

import ChungComiServer.dot.core.entity.interest.TechStack;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
@Slf4j
public class TechStackRepository {

    private final EntityManager em;

    public List<TechStack> findAll() {
        return em.createQuery("select t from TechStack t" +
                        " join fetch t.comments", TechStack.class)
                .getResultList();
    }

    public TechStack findById(Long techStackId) {
        return em.find(TechStack.class,techStackId);
    }

    public List<TechStack> findByName(String techStackName) {
        return em.createQuery("select t from TechStack t" +
                        " join fetch t.comments" +
                        " where t.name =: techStackName",TechStack.class)
                .setParameter("techStackName",techStackName)
                .getResultList();
    }

}
