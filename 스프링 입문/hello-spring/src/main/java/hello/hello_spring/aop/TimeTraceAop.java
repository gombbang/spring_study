package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component  // 컴포넌트 스캔 방식, 근데 비추. 특별한 클래스니까.
@Aspect // AOP 필수 애노테이션
public class TimeTraceAop {
    @Around("execution(* hello.hello_spring..*(..))")   // controller, service의 비즈니스 로직, repository 모두 다 확인해줌.
    //@Around("execution(* hello.hello_spring.service..*(..))")  // service만도 가능하다.
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();     // 다음 메소드 진행
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms");
            // 여기서 중간에 무슨 문제가 있으면 넘어가지마, 하는 인터셉팅도 가능하다.

        }
    }
}

// aop가 작동하면 실행하는 controller, 서비스, repository는 가짜 빈이다.
// 원래 내가 작성한 핵심 내용이 담긴 애들이 아니라, 프록시 빈으로 만들어진다.
// 그러면 스프링 컨테이너에 있는 그 가짜 빈이 돌다가, joinPoint.proceed()를 만나면 진짜 컨트롤러,서비스,리포지토리를 타고 이동하는 것.
// EnhancerBy Spring CGLIB, 기존 빈을 가지고 복제해서 코드를 조작하는 기술!

// 스프링 컨테이너가 빈을 등록하기 때문에 DI를 통해 복제된 CGLIB를 기존 빈 대신 바꿔치기가 가능한 것
// 스프링컨테이너가 관리하지 않고, DI하지않고, hello controller 에서 멤버서비스를 직접 연결하는 등의 작업을 하면
    // 이런 AOP가 불가능할 것
// Proxy 방식의 AOP
// 이건 컴파일 과정중 직접 코드를 넣어주는 AOP는 아니다.
