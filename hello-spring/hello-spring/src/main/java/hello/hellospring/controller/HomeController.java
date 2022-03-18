package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
//요청오면 관련 controller 파일 찾고 없으면 static 파일 찾음
    @GetMapping("/")
    public String home(){
        return"home";
    }
}
