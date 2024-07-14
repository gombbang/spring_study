package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {
    private final EntityManager em; // JPA는 모두 이걸로 동작한다.
    // jpa 라이브러리를 gradle에서 받은 이후로 부트가 자동으로 em을 생성해서 현재 DB와 연결까지 다 해준다. (application.properties)
    // 그걸 인젝션만 받으면 된다.
    // em은 datasource를 안에 들고 있어서 얘면 알아서 다 된다.
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }
    public Member save(Member member) {
        em.persist(member);     // 영구적으로 저장하다, persist
        return member;
    }
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);  // 조회할 타입과 식별자 PK면 됌.
        return Optional.ofNullable(member);
    }
    public List<Member> findAll() { // jpa QL을 만들어서 써야 함, entity 자체를 select하므로 m을 select의 대상으로 쓴다.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    public Optional<Member> findByName(String name) {       // jpa QL을 만들어서 써야 함.
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }
}