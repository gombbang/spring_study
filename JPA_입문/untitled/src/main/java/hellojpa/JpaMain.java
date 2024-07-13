package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // persistence-unit name="hello", persistence.xml 파일 내부
        // emf는 딱 하나만 만들어야 한다, DB당 하나만!!!!
        EntityManager em = emf.createEntityManager();
        // 트랜잭션 단위, 고객이 들어와서 어떤 행위를 하는 동안, 상품을 장바구니에 담는 등, 한가지 작업을 할 때마다
        // em을 하나씩 만들어줘야한다.
        // 요청마다 새로 만들고, 버리고, 만들고, 버리고 ...
        // 스레드마다 공유하면 절 대 안된다!!

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();

            member.setId(2L);
            member.setName("HelloB");


            Member member1 = new Member();

            member1.setId(1L);
            member1.setName("HelloA");
            em.persist(member);
            em.persist(member1);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }// data connection을 물고 하기 때문에, em은 꼭 닫아줘야한다.
        finally {
            em.close();
        }

        try {
            em = emf.createEntityManager();

            tx = em.getTransaction();
            tx.begin();

            Member findMember = em.find(Member.class, 2L);
            findMember.setName("이름을바꿨다,setName만");
                // 트랜잭션 커밋 이전에 바뀐 정보가 있는지 확인하고, 달라지면 update 쿼리를 날려준다.

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }// data connection을 물고 하기 때문에, em은 꼭 닫아줘야한다.
        finally {
            em.close();
        }

        // JPQL, 엔티티 객체를 대상으로 쿼리, SQL은 데이터베이스 테이블을 대상으로 쿼리
        // -> 방언을 써도 수정하지 않아도 된다, 자바 코딩만 하면 끝!
        // SQL을 추상화한 객체 지향 쿼리언어, 테이블이 아닌 객체를 대상으로 검색...


        try {
            em = emf.createEntityManager();

            tx = em.getTransaction();
            tx.begin();

//            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();

            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(8)       // 페이지네이션
                    .getResultList();

            for (Member m : result) {
                System.out.println("member.name = " + m.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }// data connection을 물고 하기 때문에, em은 꼭 닫아줘야한다.
        finally {
            em.close();
        }

        emf.close();

    }
}
