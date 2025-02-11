package ChungComiServer.dot.api.controller;

import ChungComiServer.dot.api.dto.GetMemberCompaniesDTO;
import ChungComiServer.dot.api.service.MemberApiService;
import ChungComiServer.dot.core.dto.member.ResponseMemberDTO;
import ChungComiServer.dot.core.service.MemberService;
import ChungComiServer.dot.global.response.ErrorCode;
import ChungComiServer.dot.global.response.Response;
import ChungComiServer.dot.global.security.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberApiController {

    private final MemberApiService memberApiService;
    private final MemberService memberService;
    private final UserContext userContext;

    /**
     * 나의 선호 기업 정보
     * @Param: memberId
     * @Return:
     * {
     * String myName,
     * List<GetCommentDetailDTO> companies {String name, Long rate }
     * }
     */
    @GetMapping("/{memberId}")
    public Response getMemberCompanies(@PathVariable String memberId){
        try{
            GetMemberCompaniesDTO myCompanies = memberApiService.findMemberCompaniesById(memberId);
            return Response.success(myCompanies);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @GetMapping("")
    public Response getMyInfo(){
        try{
            ResponseMemberDTO memberDTO = memberService.findById(userContext.getUserId());
            return Response.success(memberDTO);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }


}
