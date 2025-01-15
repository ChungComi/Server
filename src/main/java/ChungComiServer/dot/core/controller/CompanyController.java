package ChungComiServer.dot.core.controller;

import ChungComiServer.dot.core.dto.company.ResponseCompanyDTO;
import ChungComiServer.dot.core.service.CompanyService;
import ChungComiServer.dot.global.response.ErrorCode;
import ChungComiServer.dot.global.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("")
    public Response getAllCompanies(){
        try{
            List<ResponseCompanyDTO> companyDTOs = companyService.findAll();
            return Response.success(companyDTOs);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }
}
