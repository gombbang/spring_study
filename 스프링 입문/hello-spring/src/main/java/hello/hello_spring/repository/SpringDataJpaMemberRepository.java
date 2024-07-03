package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;   // 여기에 기본 메서드를 다 제공한다, Paging까지
// count까지, CRUD 기본조회 등등
// 못 만드는게 있음, 공통 인터페이스라서 되는 것 말고, findByName 같은 것.

import java.util.Optional;

// springjpa가 jpa 리포지토리를 빈으로 가지고 있으므로 자동으로 등록해준다, 직접 구현체를 만들어주기까지 함.

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    Optional<Member> findByName(String name);
    // select m from Member m wher m.name = ?
    // findByNameAndId findByNameOrId? ...
    // 단순한 80프로는 인터페이스로 완료된다.

}