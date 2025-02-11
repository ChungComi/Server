package ChungComiServer.dot.api.controller;

import ChungComiServer.dot.api.dto.GetMemberCompaniesDTO;
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


    /**
     * 나의 선호 기업 정보
     * @Return:
     * {
     * String myName,
     * List<GetCommentDetailDTO> companies {String name, Long rate }
     * }
     */
    @GetMapping("/company")
    public Response getMemberCompanies(){
        try{
            GetMemberCompaniesDTO myCompanies = memberApiService.findMemberCompaniesById(userContext.getUserId());
            return Response.success(myCompanies);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

}
