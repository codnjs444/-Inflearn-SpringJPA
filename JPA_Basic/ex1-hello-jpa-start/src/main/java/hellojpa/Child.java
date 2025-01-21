package hellojpa;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Child {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

}

