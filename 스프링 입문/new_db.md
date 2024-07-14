MemberService에서 내부에 
MemberRepository member = new MemberRepository();
이 경우 새로운 객체를 생성하게 된다.
해당 memory DB는 
memberService를 생성하는 memberTest에서 
MemoryMemberRepository memberRepository = new MemoryMemberRepository();를 할 때

memberService의 memberRepository와 다른 새로운 객체가 되어
두개의 DB가 돌게 된다.

memberService의 MemberRepository를 new로 생성하지않고,
생성자에 주입받는 식으로 수정한다.
-> memberService 객체를 만들 때, 해당 memberRepository를 주입할 수 있도록 한다.
--> 테스트 실행마다 동일한 DB를 사용하게 된다.

---
DI 방식
1. 생성자 주입
2. 필드 주입 (@Autowired)
   3. spring 뜰 때 바꿀 수 없음
3. Setter 주입
   4. 중간에 바꿀 일이 없는 일인데도 Set 함수가 생기는 것, 한번 Setting 이후 바꿀 일 없는데 public으로 열린다, 비효율적 위험성
   5. 최대한 변경 안되어야하는 작업인데도 막을 수 없다.

Runtime중 바꿔치기 할 일이 없다! 

DI 형태
1. 필드주입방식이 주로 쓰임
2. 빈파일(SpringConfig)을 통해 주입
   3. 기존 운영코드를 바꾸지 않고, 구현체를 바꿔치기해야할 때 사용한다.
      4. 특히 DB 변경에 사용될 듯

