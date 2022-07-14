package com.aswinblue.RankServer.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller  // controller를 정의하는 annotation
public class MainController {
    @GetMapping("/index")  // 연결될 url을 지정하는 annotation, /index 로 연결하면
    public String index(Model model)  // model 을 통해 가변 인자 control
    {
        // model.addAttribute("title", "hello world"); // title 이름으로 hello world 라는 문자열을 설정
        return "index";  // index.html 파일과 연동
    }
}