package hellojpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity // jpa를 사용하는 애인지 인식하고, 얘를 관리해야한다고 인식함.
@Getter
@Setter // entity가 getter setter 해주던데, jakarta는 안그런가봐?
public class Member {

    @Id // annotation은 jakarta.persistence로 해야한다.
    private Long id;
    private String name;


}
