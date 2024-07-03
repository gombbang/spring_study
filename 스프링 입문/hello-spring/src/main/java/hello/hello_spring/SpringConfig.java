package hello.hello_spring;

import hello.hello_spring.repository.*;
import hello.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// 장점 :
@Configuration
public class SpringConfig {

//    private DataSource dataSource; //스프링부트가 프로퍼티 보고 자동으로 데이터소스를 만들어 주고 주입해준다. DI

    //    @Autowired
//    public SpringConfig (DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    @PersistenceContext // 스펙에서 하라는 내용이지만 없어도 된다고 함.
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig (EntityManager em) {
//        this.em = em;
//    }

    // 어떤 리포지토리를 쓰는지 안 알려줬지만, extends Jpa Repository(스프링데이터가 만든거)가 있으면 스프링 데이터가
    // 어떤 구현체를 만들어서 그대로 넣어줘버린다.
    private final MemberRepository memberRepository;
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
