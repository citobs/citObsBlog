package dev.citobs.citblog2.test;
import org.springframework.web.bind.annotation.*;

//사용자가 요청 - 응답(HTML 파일)
//@Controller

//인터넷 브라우저 요청은 무조건 get요청밖에 없다
//사용자가 요청 -> 요청(Data)
@RestController
public class HttpControllerTest {

    private static final String TAG = "httpControllerTest";

    @GetMapping("/http/get")
    public String getTest(Member m){
        System.out.println(TAG+"getter:"+m.getId());
        m.setId(5000);
        System.out.println(TAG+"setter:"+m.getId());
        return "get요청:" +m.getId()+","+m.getEmail()+","+m.getEmail();
    }

    @PostMapping("/http/post")
    public String postTest(){
        return "포스트요청";
    }

    @PutMapping("/http/put")
    public String putTest(){
        return "풋요청";
    }

    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "델리트요청";
    }

}
