package hello.hello_spring.domain;

import jakarta.persistence.*;

@Entity
// JPA가 관리할 수 있게 해줌, PK 설정해야함.
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 알아서 생성해주는 건 IDENTITY다.
    private Long id;

//    @Column(name="username") //JPA가 테이블을 이렇게 맞춰서 만들어준다.
    private String name;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}