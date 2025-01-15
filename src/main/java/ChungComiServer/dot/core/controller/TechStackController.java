package ChungComiServer.dot.core.controller;

import ChungComiServer.dot.core.dto.interest.ResponseInterestDTO;
import ChungComiServer.dot.core.service.TechStackService;
import ChungComiServer.dot.global.response.ErrorCode;
import ChungComiServer.dot.global.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/tech-stack")
public class TechStackController {

    private final TechStackService techStackService;

    @GetMapping("")
    public Response getAllTechStacks(){
        try{
            List<ResponseInterestDTO> techStackDTOs = techStackService.findAll();
            return Response.success(techStackDTOs);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @GetMapping("/{tech-stackId}")
}
