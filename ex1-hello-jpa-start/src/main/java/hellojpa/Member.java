package hellojpa;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table
public class Member {

    @Id
    private Long id;

    @Column(name = "name", unique = true, length = 10, updatable = false)
    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lasgModifiedDate;

    private LocalDate localDate;

    private LocalDateTime localDateTime;

    @Lob
    private String description;

    @Transient
    private int temp;

}
