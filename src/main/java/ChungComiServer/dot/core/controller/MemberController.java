package ChungComiServer.dot.core.controller;

import ChungComiServer.dot.core.dto.ResponseMemberDTO;
import ChungComiServer.dot.core.entity.Member;
import ChungComiServer.dot.core.repository.MemberRepository;
import ChungComiServer.dot.core.service.MemberService;
import ChungComiServer.dot.global.response.ErrorCode;
import ChungComiServer.dot.global.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    /**
     * 여러 사람 정보
     * @Param: x
     * @Return:
     *     List[
     *     {
     *     String name;
     *
     *     List<MemberCompanyDTO> memberCompanies{
     *     Long preference;
     *     String companyName;}
     *
     *     List<MemberTechStackDTO> memberTechStacks{
     *     Long preference;
     *     String techStackName;}
     *
     *     SchoolDTO school{
     *     String name};
     *     }
     *     ]
     */
    @GetMapping("")
    public Response getAllMembers(){
        try{
            List<ResponseMemberDTO> all = memberService.findAll();
            return Response.success(all);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    /**
     * 내 정보
     * @param memberId
     * @Return: { String name; List memberCompanies{ Long preference; String companyName;} List memberTechStacks{ Long preference; String techStackName;} SchoolDTO school{ String name}; }
     */
    @GetMapping("/{memberId}")
    public Response getMemberById(@PathVariable String memberId){
        try{
            ResponseMemberDTO foundMember = memberService.findById(memberId);
            return Response.success(foundMember);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    /**
     * 내 정보
     * @param memberName
     * @return { String name; List memberCompanies{ Long preference; String companyName;} List memberTechStacks{ Long preference; String techStackName;} SchoolDTO school{ String name}; }
     */
    @GetMapping("/{memberName}")
    public Response getMemberByName(@PathVariable String memberName){
        try{
            List<ResponseMemberDTO> foundMembers = memberService.findByName(memberName);
            return Response.success(foundMembers);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }
}
