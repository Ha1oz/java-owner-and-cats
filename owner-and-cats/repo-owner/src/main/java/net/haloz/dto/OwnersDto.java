package net.haloz.dto;

import lombok.Data;

import java.util.List;
@Data
public class OwnersDto {
    private List<OwnersDto> cats;
    private int size;
}
