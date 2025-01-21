package hellojpa.jpql;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Data;

@Embeddable
@Data
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
