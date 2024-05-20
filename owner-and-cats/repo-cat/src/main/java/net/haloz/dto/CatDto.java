package net.haloz.dto;

import lombok.Data;
import net.haloz.model.Color;

@Data
public class CatDto {
    private String name;
    private String birth;
    private String breed;
    private Color color;

    public CatDto() {
    }
}
