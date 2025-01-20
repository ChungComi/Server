package ChungComiServer.dot.core.controller;

import ChungComiServer.dot.core.dto.interest.ModifyInterestDTO;
import ChungComiServer.dot.core.dto.interest.ResponseInterestDTO;
import ChungComiServer.dot.core.service.TechStackService;
import ChungComiServer.dot.global.response.ErrorCode;
import ChungComiServer.dot.global.response.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public Response getTechStackById(@PathVariable("tech-stackId")String techStackId){
        try{
            ResponseInterestDTO techStackDTO = techStackService.findById(techStackId);
            return Response.success(techStackDTO);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @GetMapping("/name/{tech-stackName}")
    public Response getTechStackByName(@PathVariable("tech-stackName")String techStackName){
        try{
            List<ResponseInterestDTO> techStackDTOs = techStackService.findByName(techStackName);
            return Response.success(techStackDTOs);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @PutMapping("/{tech-stackId}")
    public Response modifyTechInfo(@PathVariable(value = "tech-stackId")String techStackId,
                                   @RequestBody @Valid ModifyInterestDTO modifyInterestDTO, BindingResult result){
        try{
            if(result.hasErrors())
                return Response.failure(new ErrorCode(result.getFieldError().toString()));
            ResponseInterestDTO techStack = techStackService.modifyTechInfo(techStackId,modifyInterestDTO.getName()
                    ,modifyInterestDTO.getDescription());
            return Response.success(techStack);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }
}
