package ChungComiServer.dot.core.repository;

import ChungComiServer.dot.core.entity.interest.Company;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CompanyRepository {

    private final EntityManager em;

    public List<Company> findAll() {
        return em.createQuery("select c from Company c", Company.class)
                .getResultList();
    }
}
