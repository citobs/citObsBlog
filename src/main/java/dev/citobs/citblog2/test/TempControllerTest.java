package dev.citobs.citblog2.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //controller일때는 파일을 리턴함 vs restcontroller는 파일을 리턴함
public class TempControllerTest {

    //http://localhost:8000/blog/temp/home
    @GetMapping("/temp/home")
    public String tempHome() {
        System.out.println("tempHome()");
        //파일리턴경로 :src/main/resources/static
        //리턴명:/home.html
        return "/home.html";
    }

    @GetMapping("/temp/jsp")
    public String tempJsp() {
        //prefix:/WEB-INF/views
        //suffix:.jsp
        //풀네임:/WEB-INF/views//test.jsp.jsp
        return "test";
    }
}

