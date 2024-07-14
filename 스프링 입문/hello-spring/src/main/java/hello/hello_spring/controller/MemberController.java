package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 컨트롤러는 어쩔 수 없이 애노테이션으로 처리해야한다, 컨테이너가 관리하므로.
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


    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";    // home 화면으로 redirect
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
