package hellojpa;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class Period {
    LocalDateTime startDate;
    LocalDateTime endDate;
}
