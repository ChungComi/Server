package ChungComiServer.dot.global;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    protected LocalDateTime registerDate;
    protected LocalDateTime modifiedDate;
}
