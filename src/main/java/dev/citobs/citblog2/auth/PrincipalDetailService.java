package dev.citobs.citblog2.auth;

import dev.citobs.citblog2.model.User;
import dev.citobs.citblog2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //스픠링이 로그인 요청을 가로챌 때 , username, passwrod 변수 2개를 가로채는데
    //패스워드 부분처리는 알아서 함.
    //username이 DB에 있는지만 확인해서 리턴 확인해주면 함.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username)
                .orElseThrow(() ->{
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다 :" + username);
                });
        return new PrincipalDetail(principal); //시큐리티의 유저 정보가 저장됨
    }
}
