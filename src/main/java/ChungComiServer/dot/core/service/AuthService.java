package ChungComiServer.dot.core.service;

import ChungComiServer.dot.core.entity.Member;
import ChungComiServer.dot.core.entity.MemberCompany;
import ChungComiServer.dot.core.entity.MemberTechStack;
import ChungComiServer.dot.core.repository.UserRepository;
import ChungComiServer.dot.global.security.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordUtil passwordUtil;


    public String login(String loginID, String loginPW) {
        Member member = userRepository.findByLoginId(loginID);
        if(member != null && passwordUtil.matches(member.getLoginPw(),loginPW)){
            return member.getName();
        }
        return null;
    }

    @Transactional
    public Long register(String name, String loginId, String loginPw,
                           List<MemberCompany> memberCompanies, List<MemberTechStack> memberTechStacks) throws InvalidPropertiesFormatException {
        String encryptedLoginPw = passwordUtil.encrypt(loginPw);
        Member member = new Member(name,loginPw,encryptedLoginPw,memberCompanies,memberTechStacks);
        return userRepository.save(member);
    }
}
