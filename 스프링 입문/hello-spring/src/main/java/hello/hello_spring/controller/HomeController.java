package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")    // 기존 초기화면은 index.html이나, 이 GetMapping("/")으로 해당 index.html 파일은 무시된다.
    public String home() {
        return "home";
    }
}