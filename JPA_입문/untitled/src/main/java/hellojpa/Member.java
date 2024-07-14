package hellojpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity()
@Getter
@Setter
//@Table(uniqueConstraints = "id")
@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq")
public class Member {
    @Id // 직접 할당 방법
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동할당방법들, 아이덴티티는 db에게 위임하는 것, 알아서 하라고 db에게 맡김
    // auto : db 방언에 맞게 만들어줌 ㅎㅎ
//    sequence db 시퀀스 오브젝트 사용, oracle
    // call next value for hb_seq -> 나온 값을 id에 넣어 보낸다.
    //  seqence는 직접 만들 수 있어, @SequenceGenerator, 근데 굳이?
    //seq와 identi는 각 DB별로 있고 없고가 있음, 그래서 방법으로 TABLE 전략
        // 키 생성 전용 테이블을 만들어서 db 시퀀스를 흉내내는 전략인데, 모든 곳에서 쓰일 수 있지만 성능 개판이라고 함.

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    private Long id;    // int는 초기화 데이터가 0이므로 애매, Integer도 애매.. sequence object는 10억 이상에서 한바퀴 끝남, 고로 Long

    @Column(name = "name")  //DB에서는 NAME으로 들어간다.
    private String username;
    private Integer age;
    @Enumerated(EnumType.STRING)    // DB에는 enum이 기본적으로 없다, -> Enumerated. -> VARCHAR...
    private RoleType roleType;
    // ordinary 말고 string을 쓰자, enum 최신화중 순서가 바뀌거나 새로운게 앞으로 가면 내부 데이터가 섞여 해결 불가.
    @Temporal(TemporalType.TIMESTAMP) // TemporalType은 DATE TIME TIMESTAMP 3가지.
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Lob        // 큰 사이즈의 데이터, 문자타입이니 CLOB
    private String description;

    @Transient // 메모리에서만 쓰고 DB에서는 관리하지 않고 싶다.
    private int temp;

    private LocalDate testLocalDate;    // temporal이 없어도 된다, type보면 아니까..
    private LocalDateTime testLocalDateTime; // 이 2개는 최신버전, 위는 과거버전. 과거버전은 맞게 맵핑 필요하다고 함.

    // updateable : 한번 생성 된 이후 절대 변경되서는 안되는 값일 경우 이걸로 처리한다, false로
    // insertable :
    // nullable : not null 제약조건, false
    // unique : 유니크 제약조건, 근데 잘 안쓴다, UK이름이 내가 알아 볼 수 없어 운영에서 쓸 수 없기 때문이다.
        // 운영시 UK를 보고 어디서 문제가 발생했는지 알아야 하는데, 그게 안되므로
            // -> Table 애노테이션에서 처리해야한다.
    // column definition : 직접 컬럼 내용 처리 가능, "varchar(100) default 'EMPTY'"
    // precision, scale : 크거나 매우 작은 수 처리

    // JPA에서 쓰려면 public으로 만든 생성자가 꼭 필요하다.
    public Member() {

    }
}

//@Entity // jpa를 사용하는 애인지 인식하고, 얘를 관리해야한다고 인식함.
//@Getter
//@Setter // entity가 getter setter 해주던데, jakarta는 안그런가봐?
//public class Member {
//
//    @Id // annotation은 jakarta.persistence로 해야한다.
//    private Long id;
//    private String name;
//
//
//}


