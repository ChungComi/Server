package ChungComiServer.dot.global.security.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class PasswordUtil {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String encrypt(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean matches(String password, String encryptedPassword){
        return bCryptPasswordEncoder.matches(password,encryptedPassword);
    }

}
