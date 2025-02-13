package ChungComiServer.dot.global;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class BaseEntity {

    protected LocalDateTime registerDate;
    protected LocalDateTime modifiedDate;
}
