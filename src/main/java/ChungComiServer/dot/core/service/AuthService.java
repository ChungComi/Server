package ChungComiServer.dot.core.service;

import ChungComiServer.dot.core.entity.Member;
import ChungComiServer.dot.core.repository.MemberRepository;
import ChungComiServer.dot.global.security.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.InvalidPropertiesFormatException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordUtil passwordUtil;


    public Long login(String loginID, String loginPW) {
        Member member = memberRepository.findByLoginId(loginID);
        if(member != null && passwordUtil.matches(member.getLoginPw(),loginPW)){
            return member.getId();
        }
        return null;
    }

    @Transactional
    public Long register(String name, String loginId, String loginPw) throws InvalidPropertiesFormatException {
        Member member = new Member(name,loginId,loginPw);
        String encryptedLoginPw = passwordUtil.encrypt(loginPw);
        member.changePwToEncrypt(encryptedLoginPw);
        return memberRepository.save(member);
    }
}
