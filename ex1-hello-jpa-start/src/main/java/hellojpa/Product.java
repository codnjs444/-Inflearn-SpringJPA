package hellojpa;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "product")
    private List<MemberProducts> products = new ArrayList<>();


}
