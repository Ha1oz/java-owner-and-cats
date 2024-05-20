package net.haloz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.haloz.dto.CatsDto;
import net.haloz.model.Color;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity(name = "cat")
@Data
public class Cat {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private LocalDate birth;
    private String breed;
    private Color color;
    @OneToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Cat friends;

    public Cat() {
    }
}

