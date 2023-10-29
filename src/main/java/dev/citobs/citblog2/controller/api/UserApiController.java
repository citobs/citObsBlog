package dev.citobs.citblog2.controller.api;

import dev.citobs.citblog2.auth.PrincipalDetailService;
import dev.citobs.citblog2.dto.ResponseDto;
import dev.citobs.citblog2.model.RoleType;
import dev.citobs.citblog2.model.User;
import dev.citobs.citblog2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encode;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PrincipalDetailService principalDetailService;


//    @Autowired
//    private HttpSession session;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserAPI save호출됨");
//        user.setRole(RoleType.USER);
        //실제로 DB에 Insert를 하고 아래에서 return이 되면 된다.
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK, 1);

    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user) { // key=value, x-www-form-urlencoded
        userService.회원수정(user);
        // 여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 됐음.
        // 하지만 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해줄 것임.
        // 세션 등록

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseDto<Integer>(HttpStatus.OK, 1);
    }

//    @PostMapping("/api/user/login")
//    //사용할려면 @Autowired httpsession session
//    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//        System.out.println("UserAPI호출됨:login");
//        User principal = userService.로그인(user); //principal(접근주체)
//
//        if(principal != null) {
//            session.setAttribute("principal", principal);
//        }
//        return new ResponseDto<Integer>(HttpStatus.OK, 1);
//    }
}
