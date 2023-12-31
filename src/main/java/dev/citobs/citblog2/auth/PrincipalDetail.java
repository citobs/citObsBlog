package dev.citobs.citblog2.auth;

import dev.citobs.citblog2.model.User;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class PrincipalDetail implements UserDetails {
    private User user; //컴포지션

    public PrincipalDetail(User user){
        this.user = user;
    }

    // 스프링시큐리티가 로그인 요청을 가로채서 완료가 되면 UserDetail 타입의 오브젝트를
    //스프링 시큐리티의 고유한 세션저장소에 저장해준다.
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //계정이 만료되지 않았는지 리턴한다(true"만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨있찌 않았는지 리턴
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호가 만료되지 않았는지 리턴(true:만료되지 않음)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정활성화가 되어있는가?(true:활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    //계정이 갖고있는 권한 목록을 리턴한다.(권한이 여러개 있을 수 있어서 루프를 돌아야 하는데 한개만 한다)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_"+user.getRole(); // 리턴값 : ROLE_USER
            }
        });
        return collector;
    }
}
