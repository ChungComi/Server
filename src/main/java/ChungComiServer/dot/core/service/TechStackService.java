package ChungComiServer.dot.core.service;

import ChungComiServer.dot.core.dto.interest.ResponseInterestDTO;
import ChungComiServer.dot.core.entity.interest.Interest;
import ChungComiServer.dot.core.entity.interest.TechStack;
import ChungComiServer.dot.core.repository.TechStackRepository;
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
public class TechStackService {

    private final TechStackRepository techStackRepository;

    public List<ResponseInterestDTO> findAll() {
        List<TechStack> techStacks = techStackRepository.findAll();
        if(techStacks.isEmpty()){
            throw new NoSuchElementException("등록된 기술 스택이 존재하지 않습니다.");
        }
        return techStacks.stream().map(ResponseInterestDTO::new).toList();
    }


    public ResponseInterestDTO findById(String stringTechStackId) {
        Long techStackId = Long.valueOf(stringTechStackId);
        TechStack techStack = techStackRepository.findById(techStackId);
        if(techStack == null)
            throw new NoSuchElementException("해당 기술 스택이 존재하지 않습니다.");
        return new ResponseInterestDTO(techStack);
    }

    public List<ResponseInterestDTO> findByName(String techStackName) {
        List<TechStack> techStacks = techStackRepository.findByName(techStackName);
        if(techStacks.isEmpty()){
            throw new NoSuchElementException("등록된 기술 스택이 존재하지 않습니다.");
        }
        return techStacks.stream().map(ResponseInterestDTO::new).toList();
    }

    @Transactional
    public ResponseInterestDTO modifyTechInfo(String stringTechStackId, String name, String description) {
        Long techStackId = Long.valueOf(stringTechStackId);
        TechStack techStack = techStackRepository.findById(techStackId);
        if(techStack == null)
            throw new NoSuchElementException("해당 기술 스택이 존재하지 않습니다.");
        techStack.modifyInfo(name,description);
        return new ResponseInterestDTO(techStack);
    }

    @Transactional
    public Long register(String name, String description) {
        Interest techStack = new TechStack(name,description);
        Long techStackId = techStackRepository.register(techStack);
        if(techStackId == null)
            throw new RuntimeException("기업 저장 실패");
        return techStackId;
    }
}
