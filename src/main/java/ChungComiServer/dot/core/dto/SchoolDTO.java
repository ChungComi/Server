package ChungComiServer.dot.core.dto;

import lombok.Getter;

@Getter
public class SchoolDTO {
    String name;
    public SchoolDTO(){}

    public SchoolDTO(String name){
        this.name = name;
    }
}
