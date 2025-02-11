package ChungComiServer.dot.api.controller;

import ChungComiServer.dot.api.service.MemberApiService;

import ChungComiServer.dot.core.dto.member.ResponseMemberDTO;
import ChungComiServer.dot.core.dto.school.SchoolDTO;
import ChungComiServer.dot.core.service.MemberService;
import ChungComiServer.dot.global.response.ErrorCode;
import ChungComiServer.dot.global.response.Response;
import ChungComiServer.dot.global.security.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberApiController {

    private final MemberApiService memberApiService;
    private final MemberService memberService;
    private final UserContext userContext;

    @GetMapping("")
    public Response getMyInfo(){
        try{
            ResponseMemberDTO memberDTO = memberService.findById(userContext.getUserId());
            return Response.success(memberDTO);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @PostMapping("/school")
    public Response registerSchool(@RequestBody SchoolDTO schoolDTO){
        try{
            Long schoolId = memberApiService.registerSchool(userContext.getUserId(),schoolDTO.getName());
            return Response.success(schoolId);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @PostMapping("/company/{companyName}")
    public Response setMemberCompaniesByName(@PathVariable("companyName")String companyName){
        try{
            Long memberCompanyId = memberApiService.setMemberCompaniesByName(userContext.getUserId(), companyName);
            return Response.success(memberCompanyId);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @PostMapping("/tech-stack/{tech-stackName}")
    public Response setMemberTechStackByName(@PathVariable("tech-stackName")String techStackName){
        try{
            Long memberTechStackId = memberApiService.setMemberTechStackByName(userContext.getUserId(),techStackName);
            return Response.success(memberTechStackId);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @DeleteMapping("/company/{companyName}")
    public Response deleteMemberCompaniesByName(@PathVariable("companyName")String companyName){
        try{
            memberApiService.deleteMemberCompanyByName(userContext.getUserId(),companyName);
            return Response.success();
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @DeleteMapping("/tech-stack/{tech-stackName}")
    public Response deleteMemberTechStackByName(@PathVariable("tech-stackName")String techStackName){
        try{
            memberApiService.deleteMemberTechStackByName(userContext.getUserId(),techStackName);
            return Response.success();
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

}
