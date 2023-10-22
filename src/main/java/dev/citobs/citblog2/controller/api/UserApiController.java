package dev.citobs.citblog2.controller.api;

import dev.citobs.citblog2.dto.ResponseDto;
import dev.citobs.citblog2.model.RoleType;
import dev.citobs.citblog2.model.User;
import dev.citobs.citblog2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encode;

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
