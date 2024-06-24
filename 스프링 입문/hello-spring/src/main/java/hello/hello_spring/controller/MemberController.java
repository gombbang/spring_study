package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    //Component Scan 방식, Service Controller Repository 모두 Componenet라는 애노테이션이 붙어있음. (자동의존관계 설정)
    // Component 스캔은 Application의 하위 패키지만 등록된다, 추가 설정을 해야 타 패키지도 등록된다.
    @Autowired  // spring container에서 가져옴, Dependency Injection,DI.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    // 여러 인스턴스 생성이 필요없이 공용의 인스턴스면 되므로, spring container에 넣는다.


}
