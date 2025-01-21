package hellojpa.jpql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    private int age;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

}
