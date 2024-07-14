package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// spring이 이 서비스를 컨테이너에 넣기위한 방법, @Service
@Transactional
// JPA는 항상 Transactional이 필요하다!
// 굳이 class 자체에 걸지 않아도 되고, 필요한 로직, 여기서는 join에만 넣어줘도 된다.
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    /**
     * 회원가입
     */
    public Long join(Member member) {
        long start = System.currentTimeMillis();
        try {
            validateDuplicateMember(member); //중복 회원 검증
            memberRepository.save(member);
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
//        회원가입, 회원 조회에 시간을 측정하는 기능은 핵심 관심 사항이 아니다.
//시간을 측정하는 로직은 공통 관심 사항이다.
//시간을 측정하는 로직과 핵심 비즈니스의 로직이 섞여서 유지보수가 어렵다.
//시간을 측정하는 로직을 별도의 공통 로직으로 만들기 매우 어렵다.
//시간을 측정하는 로직을 변경할 때 모든 로직을 찾아가면서 변경해야 한다.
        
        // 핵심 관심 사항 core concern
        // 공통 관심 사항 cross-cutting concern
        
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}