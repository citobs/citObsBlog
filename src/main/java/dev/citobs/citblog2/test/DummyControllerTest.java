package dev.citobs.citblog2.test;

import dev.citobs.citblog2.model.RoleType;
import dev.citobs.citblog2.model.User;
import dev.citobs.citblog2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {
    @Autowired //의존성 주입
    private UserRepository userRepository;

    //password, email 수정
    @Transactional
    @PutMapping("/dummy/user/{id}")
    //json데이터 가져오기 @requestBody
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){
        System.out.println("id :"+id);
        System.out.println("password :"+requestUser.getPassword());
        System.out.println("email :"+requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() ->{
            return new IllegalArgumentException("수정에 실패했다.");
        });

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

//        requestUser.setId(id);
//        //update할때는 save를 쓰지 않는다.
//        //자바는 파라미터에 함수를쓸수없지만, 람다식은 쓸 수 있음.
//        //save를 함수를 id를 전달하지 않으면 insert를 해주고
//        //save 함수는 id를 전달하면 해당 id에 대한 데이터가있으며 update
//        //save 함수는 id를 전달하면 해당id에 대한 데이터가없으면 insert를함
////        userRepository.save(requestUser);

        //save가 없는데도 저장됨, 더티체킹이라고 함

        return null;
    }

    //
    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user")
    //http://127.0.0.1:8000/blog/dummy/user/page?page=0
    public List<User> pageList(@PageableDefault(size=2, sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
       Page<User> pagingUser = userRepository.findAll(pageable);

       List<User> users = pagingUser.getContent();
       return users;
    }

    //{id} 주소로 파라미터로 전달 받을 수 있음
    //localhost:8000의 /blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new  IllegalArgumentException("해당 user가 없습니다. id"+id);
            }
        });

        //요청 : 웹브라우저
        //user 갳게 = 자바오브젝트
        // 변환(웹브라우저가 이해할 수 있는 데티터) ->json (Gson라이브러리)
        //스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
        //만약에 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
        return user;
    }

    //http://localhost:8000/blog/dummy/join 요청
    //http의 body에 username에 username, password, email데이터를 가지고 요청
    @PostMapping("/dummy/join")
    public String join(User user){
        //key=value(약속된 규칙)
        System.out.println("id :"+user.getId());
        System.out.println("username :"+user.getPassword());
        System.out.println("password :"+user.getPassword());
        System.out.println("email :"+user.getEmail());
        System.out.println("role :"+user.getRole());
        System.out.println("createDate :"+user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user); // db에 저장
        return "회원가입이 완료되었습니다";
    }


}
