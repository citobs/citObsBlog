package dev.citobs.citblog2.service;

import dev.citobs.citblog2.model.RoleType;
import dev.citobs.citblog2.model.User;
import dev.citobs.citblog2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service //스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌,ioc해줌
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    //서비스함수 만들기
    @Transactional
    public void 회원가입(User user) {
        String rawPassword = user.getPassword(); //password 원문
        String encPassword = encoder.encode(rawPassword); //hash
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
        System.out.println("hashpassword :" + encPassword);
    }

    @Transactional
    public void 회원수정(User user) {
        // 수정시에는 Jpa 영속성 컨텍스트 User Object를 영속화시키고, 영속화된 User object를 수정
        // select를 해서 User 오브젝트르 DB로 부터 가져오는 이유는 영속화를 하기 위해서
        // 영속화된 오브젝트를 변경하면 자동으로 db에 update문을 날려줌
        User persistence = userRepository.findById(user.getId()).orElseThrow(()->{
            return new IllegalArgumentException("회원찾기 실패");
        });

        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        persistence.setPassword(encPassword);
        persistence.setEmail(user.getEmail());

        userRepository.save(persistence);

    }

//    @Transactional(readOnly = true) //select할때 트랜잭션 시작, 서비스 종료시 트랜잭션 종료 (정합성 유지)
//    public User 로그인(User user) {
//        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//    }
}
