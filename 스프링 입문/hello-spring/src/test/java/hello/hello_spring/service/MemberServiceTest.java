package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @Test
    void 회원가입_join이라고해도됌() {
        // given, 이런 상황이 주어졌어
        // when, 이걸 실행했을 때
        // then, 이런 결과가 나와야 돼
        // 테스트가 길 때 이걸로 가져가면 매우 편함, 머리가슴배 딱 떨어지는게 테스트다.

//Given
        Member member = new Member();
        member.setName("hello");
        //When
        Long saveId = memberService.join(member);
        //Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }
    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //When
        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail();
//        }catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        assertThrows(IllegalStateException.class -> )
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
    @Test
    void findMembers_멤버있나찾기() {
    }

    @Test
    void findOne_멤버하나있나찾기() {
    }
}