package dev.citobs.citblog2.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
//특정 어노테이션에 붙어잇는 클래스 파일들을  new해서(ioc)스프링 컨테이너에서 관리
public class BlogControllerTest {

    @GetMapping("/test/hello")
    public String hello(){
        return "<h1>hello spring reboot</h1>";
    }
}