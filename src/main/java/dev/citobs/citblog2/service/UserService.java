package dev.citobs.citblog2.service;

import dev.citobs.citblog2.model.User;
import dev.citobs.citblog2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service //스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌,ioc해줌
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //서비스함수 만들기
    @Transactional
    public void 회원가입(User user) {
       userRepository.save(user);
    }
}
