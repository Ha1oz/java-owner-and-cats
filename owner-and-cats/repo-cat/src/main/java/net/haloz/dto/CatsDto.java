package net.haloz.dto;

import lombok.Data;

import java.util.List;

@Data
public class CatsDto {
    private List<CatDto> cats;
    private int size;
}
