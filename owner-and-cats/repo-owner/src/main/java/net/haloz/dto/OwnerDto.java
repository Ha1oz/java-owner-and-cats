package net.haloz.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class OwnerDto {
    private String name;
    private LocalDate birth;

    public OwnerDto() {
    }
}
