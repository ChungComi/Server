package ChungComiServer.dot.core.repository;

import ChungComiServer.dot.core.entity.School;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SchoolRepository {

    private final EntityManager em;

    public Long save(School school){
        em.persist(school);
        return school.getId();
    }
}
