package ChungComiServer.dot.core.dto.school;

import lombok.Getter;

@Getter
public class SchoolDTO {
    private String name;
    public SchoolDTO(){}

    public SchoolDTO(String name){
        this.name = name;
    }
}
