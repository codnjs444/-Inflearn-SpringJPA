package hellojpa;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn
@Data
public abstract class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;
}
