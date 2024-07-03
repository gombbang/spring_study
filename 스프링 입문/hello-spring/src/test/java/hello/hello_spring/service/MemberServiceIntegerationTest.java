package hello.hello_spring.service;
import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// DB는 트랜잭션이 있다.
// insert 쿼리 한 뒤
// 커밋을 해야 데이터가 들어간다.
// auto commit 모드로 되어있을 뿐임.
// -> 롤백을 통해 에러 없이 검증만 할 수 있음 == @Transactional
// 테스트가 끝날 때, 트랜잭션이 롤백을 한다, 반영을 안한다.
@SpringBootTest // 진짜 스프링을 띄워서 테스트를 한다는 애노테이션
@Transactional  // 테스트 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 롤백한다. 테스트 하나하나마다 한다.!!! => DB에 반영을 안하므로, 다음 테스트에 영향을 주지 않는다.
    // Test가 붙은 내용만 반영하지 않고, @Commit을 붙여버리면 커밋해버린다.
    // JAVA코드로 최소한의 단위로 하는게 단위테스트
    // 스프링과 DB등 과 함께 하는 것이 통합테스트.
    // 단위 테스트가 제일 좋다!
    // 컨테이너까지 어쩔 수 없이 올리는 거면 테스트 설계가 잘못되었을 수도 있다.
    // 회원가입() 처럼 단위 테스트로만 최대한 도는게 제일 좋다.
class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("hello");
        //When
        Long saveId = memberService.join(member);
        //Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }
//    @Test
//    public void 중복_회원_예외() throws Exception {
//        /*
//        //Given
//        Member member1 = new Member();
//        member1.setName("spring");
//        Member member2 = new Member();
//        member2.setName("spring");
//        //When
//        memberService.join(member1);
//        IllegalStateException e = assertThrows(IllegalStateException.class,() -> memberService.join(member2));//예외가 발생해야 한다.
//        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        */
//    }
}
