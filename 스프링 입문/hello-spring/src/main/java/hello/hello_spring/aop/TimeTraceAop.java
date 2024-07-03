package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component  // 컴포넌트 스캔 방식, 근데 비추. 특별한 클래스니까.
@Aspect // AOP 필수 애노테이션
public class TimeTraceAop {
    @Around("execution(* hello.hello_spring..*(..))")   // controller, service의 비즈니스 로직, repository 모두 다 확인해줌.
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();     // 다음 메소드 진행
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms");
        }
    }
}