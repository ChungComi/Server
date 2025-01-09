package ChungComiServer.dot.core.service;

import ChungComiServer.dot.core.entity.Member;
import ChungComiServer.dot.core.repository.UserRepository;
import ChungComiServer.dot.global.security.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
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
}
