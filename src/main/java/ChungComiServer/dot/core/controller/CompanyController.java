package ChungComiServer.dot.core.controller;

import ChungComiServer.dot.core.dto.interest.ModifyInterestDTO;
import ChungComiServer.dot.core.dto.interest.RegisterInterestDTO;
import ChungComiServer.dot.core.dto.interest.ResponseInterestDTO;
import ChungComiServer.dot.core.entity.interest.Company;
import ChungComiServer.dot.core.service.CompanyService;
import ChungComiServer.dot.global.response.ErrorCode;
import ChungComiServer.dot.global.response.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/company")
public class
CompanyController {

    private final CompanyService companyService;

    @GetMapping("")
    public Response getAllCompanies(){
        try{
            List<ResponseInterestDTO> companyDTOs = companyService.findAll();
            return Response.success(companyDTOs);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @GetMapping("/{companyId}")
    public Response getCompanyById(@PathVariable("companyId") String companyId){
        try{
            ResponseInterestDTO companyDTO = companyService.findById(companyId);
            return Response.success(companyDTO);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @GetMapping("/name/{companyName}")
    public Response getCompanyByName(@PathVariable("companyName") String companyName){
        try{
            List<ResponseInterestDTO> companyDTOs = companyService.findByName(companyName);
            return Response.success(companyDTOs);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    /** 개발자가 기업 등록 시 사용할 메서드 x **/
    @PostMapping("/sign-up")
    public Response registerCompany(@Valid @RequestBody RegisterInterestDTO registerCompanyDTO, BindingResult result){
        try {
            if (result.hasErrors())
                return Response.failure(new ErrorCode(result.getFieldError().toString()));
            Long companyId = companyService.register(registerCompanyDTO.getName(),registerCompanyDTO.getDescription());
            return Response.success(companyId);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    /** 개발자가 기업 정보 변경을 위해 사용할 메서드**/
    @PutMapping("/{companyId}")
    public Response modifyCompanyInfo(@PathVariable("companyId") String companyId,
            @RequestBody @Valid ModifyInterestDTO modifyInterestDTO,BindingResult result){
        try{
            if(result.hasErrors())
                return Response.failure(new ErrorCode(result.getFieldError().toString()));
            ResponseInterestDTO company = companyService.modifyCompanyInfo(companyId,modifyInterestDTO.getName()
                    ,modifyInterestDTO.getDescription());
            return Response.success(company);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }
}
