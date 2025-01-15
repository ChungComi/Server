package ChungComiServer.dot.core.service;

import ChungComiServer.dot.core.dto.interest.ResponseInterestDTO;
import ChungComiServer.dot.core.entity.interest.Company;
import ChungComiServer.dot.core.entity.interest.Interest;
import ChungComiServer.dot.core.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<ResponseInterestDTO> findAll() {
        List<Company> companies = companyRepository.findAll();
        if(companies.isEmpty()){
            throw new NoSuchElementException("등록된 기업이 존재하지 않습니다.");
        }
        return companies.stream().map(ResponseInterestDTO::new).toList();
    }

    public ResponseInterestDTO findById(String StringCompanyId) {
        Long companyId = Long.valueOf(StringCompanyId);
        Company company = companyRepository.findById(companyId);
        if(company == null)
            throw new NoSuchElementException("해당 기업이 존재하지 않습니다.");
        return new ResponseInterestDTO(company);
    }

    public List<ResponseInterestDTO> findByName(String companyName) {
        List<Company> companies = companyRepository.findByName(companyName);
        if(companies.isEmpty()){
            throw new NoSuchElementException("해당 기업이 존재하지 않습니다.");
        }
        return companies.stream().map(ResponseInterestDTO::new).toList();
    }

    @Transactional
    public Long register(String companyName,String companyDescription) {
        Interest company = new Company(companyName,companyDescription);
        Long companyId = companyRepository.save(company);
        if(companyId == null)
            throw new RuntimeException("기업 저장 실패");
        return companyId;
    }
}
