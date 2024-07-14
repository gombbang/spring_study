package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

// 테스트는 순서가 보장되지 않는다. -> 각 테스트는 독립적이어야 한다.
    // 때문에 테스트 설계는 그 순서와 관계없이 서로 의존관계없이 설계되어야한다.
        // 저장소나 공용데이터를 매번 지워줘야한다, AfterEach
// 클래스 단위, 패키지 단위 테스트가 가능하다.

// 테스트 프레임을 먼저 만들고 구현 클래스를 만드는게 TDD.
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach                  // 메서드가 끝날때마다 호출되는 메서드
    public void afterEach() {
        repository.clearStore();
    }
    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        repository.save(member); // -> save에서 member의 id를 setting
        //then
        Member result = repository.findById(member.getId()).get();
        System.out.println("Resulut =  " + (member == result));
        Assertions.assertEquals(result,member); // junit
        assertThat(result).isEqualTo(member);   // assertj, read하기 쉬운 코드.
    }
    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        Member result = repository.findByName("spring1").get();
        //then
        assertThat(result).isEqualTo(member1);
//        assertThat(result).isEqualTo(member2); // fail code
    }
    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        List<Member> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }
}