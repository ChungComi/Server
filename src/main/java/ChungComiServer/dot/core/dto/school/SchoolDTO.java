package ChungComiServer.dot.core.dto.school;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolDTO {
    private String name;
    public SchoolDTO(){}

    public SchoolDTO(String name){
        this.name = name;
    }
}
