package jpabook.jpashop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Embeddable
@Getter
@EqualsAndHashCode
public class Address {

    @Column(length = 10)
    private String city;

    @Column(length = 10)
    private String street;

    @Column(length = 10)
    private String zipcode;
}
