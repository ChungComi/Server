package ChungComiServer.dot.core.repository;

import ChungComiServer.dot.core.entity.interest.Company;
import ChungComiServer.dot.core.entity.interest.Interest;
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
        return em.createQuery("select c from Company c" +
                        " join fetch c.comments", Company.class)
                .getResultList();
    }

    public Company findById(Long companyId) {
        return em.find(Company.class,companyId);
    }

    public List<Company> findByName(String companyName) {
        return em.createQuery("select c from Company c" +
                        " join fetch c.comments" +
                        " where c.name =: companyName", Company.class)
                .setParameter("companyName",companyName)
                .getResultList();
    }

    public Long save(Interest company) {
        em.persist(company);
        return company.getId();
    }
}
