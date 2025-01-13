package ChungComiServer.dot.core.controller;

import ChungComiServer.dot.core.dto.ResponseMemberDTO;
import ChungComiServer.dot.core.entity.Member;
import ChungComiServer.dot.core.repository.MemberRepository;
import ChungComiServer.dot.core.service.MemberService;
import ChungComiServer.dot.global.response.ErrorCode;
import ChungComiServer.dot.global.response.Response;
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

    @GetMapping("")
    public Response getAllMembers(){
        try{
            List<ResponseMemberDTO> all = memberService.findAll();
            return Response.success(all);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

    @GetMapping("/{memberId}")
    public Response getMemberById(@PathVariable String memberId){
        try{
            ResponseMemberDTO foundMember = memberService.findById(memberId);
            return Response.success(foundMember);
        }catch (Exception e){
            return Response.failure(new ErrorCode(e.getMessage()));
        }
    }

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
