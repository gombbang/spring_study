영속성 컨텍스트

em.persist는 DB에 저장한다의 의미가 아니라
영속성 컨텍스트에 넣는다는 의미이다.
em.persist할 때 PK를 기준으로 하는데, PK를 Generate Strategy로 하면
PK 값을 모르게 된다.
때문에 commit 전에 DB에 먼저 날려보는 행위를 하게된다.


Auto Increment, Identity 전략
1. identity는 db에 insert query를 날려보아야 PK를 알 수 있다.
2. identity 인 경우에는 persist를 하자마자 바로 commit을 한다.
3. jpa가 pk 값을 select해서 db에서 가져온다.
4. 그 값을 영속성 컨텍스트에 담아둔다.

Sequence 전략
Sequence Object도 DB에서 관리하기 때문에, 이것도 DB에 다녀와야 값을 알 수 있다.
persist하는 시점에 db에게 seqeunce 다음 value를 가져오도록 요청함.
call next value for ~Seqence
이 값을 id에 저장해둠.
트랜잭션 commit 하는 시점에 insert query를 날림.
-> 버퍼링이 가능하다.
BUT 이건 2번의 네트워크 과정이 있네? seq 값 얻어오고 -> insert query 날리고
비효율을 방지하는 방법이 있다!
allocationSize
-> next call할 때 db에서 해당 갯수만큼 가져온다.
메모리에서 갯수만큼 미리 db에 올려놓꼬 쓰는 것
2번 호출한다.
처음에는 1, db seq
두번째는 51,
DB SEQ 1   | now 1
DB SEQ 51 | now 2
DB SEQ 51 | now 3
웹 서버를 날리는 시점에 구멍이 생긴다, 그 다음 50개를 호출하니까
-> 만개 이렇게 잡지 말고 50개 이런식으로 잡는걸 추천

				근데 동시성 이슈 없이 해결이 된다..?
					서버 별로? 50까지 받으면 그 다음은 100 받고 이런식으로 처리하기 때문에 동시성 문제 없다.