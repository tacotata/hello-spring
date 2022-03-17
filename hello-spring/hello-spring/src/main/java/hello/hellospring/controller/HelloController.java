package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hell-mvc")
//    ?name=spring!!
    //required 기본이 true 그럼 넘겨줘야함
    public String helloMvc (@RequestParam(value="name") String name , Model model){
        model.addAttribute("name",name );
        return "hello-template";
    }

    @GetMapping("hell-string")
    //응답 body에 직접 넣어주겠어
    // 문자가 그대로 나옴 html 태그 이런거 없음
    @ResponseBody
    public String helloString (@RequestParam("name") String name ){
        return "hello" + name;
    }

    //이것 때문에 api 많이 사용함

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        //기본은 json 방식으로 리턴함
    }

    // 이렇게 static 만들면 HelloController.Hello 할 수 있음
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}