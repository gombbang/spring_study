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
@Table(uniqueConstraints = "id")
public class Member {
    @Id
    private Long id;
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


