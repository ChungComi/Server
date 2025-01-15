package ChungComiServer.dot.core.controller;

import ChungComiServer.dot.core.dto.interest.RegisterInterestDTO;
import ChungComiServer.dot.core.dto.interest.ResponseInterestDTO;
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
    public Response getCompanyById(@PathVariable String companyId){
        try{
            ResponseInterestDTO companyDTO = companyService.findById(companyId);
            return Response.success(companyDTO);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @GetMapping("/{companyName}")
    public Response getCompanyByName(@PathVariable String companyName){
        try{
            List<ResponseInterestDTO> companyDTOs = companyService.findByName(companyName);
            return Response.success(companyDTOs);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @PostMapping("/sign-up")
    public Response registerCompany(@Valid @RequestBody RegisterInterestDTO registerCompanyDTO, BindingResult result){
        try {
            if (result.hasErrors())
                return Response.failure(new ErrorCode(result.getFieldError().toString()));
            Long companyId = companyService.register(registerCompanyDTO.getName());
            return Response.success(companyId);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }
}
