package ChungComiServer.dot.core.service;

import ChungComiServer.dot.core.dto.company.ResponseCompanyDTO;
import ChungComiServer.dot.core.entity.interest.Company;
import ChungComiServer.dot.core.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<ResponseCompanyDTO> findAll() {
        List<Company> companies = companyRepository.findAll();
        if(companies.isEmpty()){
            throw new NoSuchElementException("등록된 기업이 존재하지 않습니다.");
        }
        return companies.stream().map(ResponseCompanyDTO::new).toList();
    }
}
