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

