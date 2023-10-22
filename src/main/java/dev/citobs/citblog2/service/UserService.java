package dev.citobs.citblog2.service;

import dev.citobs.citblog2.model.RoleType;
import dev.citobs.citblog2.model.User;
import dev.citobs.citblog2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @Transactional(readOnly = true) //select할때 트랜잭션 시작, 서비스 종료시 트랜잭션 종료 (정합성 유지)
//    public User 로그인(User user) {
//        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//    }
}
