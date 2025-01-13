package ChungComiServer.dot.core.dto;

import ChungComiServer.dot.core.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseMemberDTO {

    private String name;
    private List<PostDTO> posts;
    private List<MemberCompanyDTO> memberCompanies;
    private List<MemberTechStackDTO> memberTechStacks;
    private SchoolDTO school;

    public ResponseMemberDTO(){}

    public ResponseMemberDTO(Member member){
        /** 반환할 이름 DTO 설정 **/
        this.name = member.getName();

        /** 반환할 게시글 DTO 설정 **/
        List<PostDTO> postsDTOs = member.getPosts()
                .stream().map(post ->
                        new PostDTO(post.getTitle(),post.getContent(), post.getLikes(), post.getViews()))
                .toList();
        this.posts = postsDTOs;

        /** 반환할 선호 기업 DTO 설정 **/
        List<MemberCompanyDTO> memberCompanyDTOs = member.getMemberCompanies()
                .stream().map(memberCompany -> new MemberCompanyDTO(memberCompany.getPreference(),memberCompany.getCompany().getName()))
                .toList();
        this.memberCompanies = memberCompanyDTOs;

        /** 반환할 선호 기술스택 DTO 설정 **/
        List<MemberTechStackDTO> memberTechStackDTOs = member.getMemberTechStacks()
                .stream().map(memberTechStack -> new MemberTechStackDTO(memberTechStack.getPreference(),memberTechStack.getTechStack().getName()))
                .toList();
        this.memberTechStacks = memberTechStackDTOs;

        /** 반환할 학교 DTO 설정 **/
        this.school = new SchoolDTO(member.getSchool().getName());
    }
}
