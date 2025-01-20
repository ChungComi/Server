package ChungComiServer.dot.core;

import ChungComiServer.dot.core.repository.CompanyRepository;
import ChungComiServer.dot.core.repository.MemberRepository;
import ChungComiServer.dot.core.repository.PostRepository;
import ChungComiServer.dot.core.repository.TechStackRepository;
import ChungComiServer.dot.core.service.*;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class ServiceTest {

    @Autowired
    protected CompanyService companyService;

    @Autowired
    protected CompanyRepository companyRepository;

    @Autowired
    protected TechStackService techStackService;

    @Autowired
    protected TechStackRepository techStackRepository;

    @Autowired
    protected MemberService memberService;

    @Autowired
    protected MemberRepository memberRepository;

    @Autowired
    protected AuthService authService;

    @Autowired
    protected PostService postService;

    @Autowired
    protected PostRepository postRepository;

}