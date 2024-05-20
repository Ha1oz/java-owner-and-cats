package net.haloz.entity;

import jakarta.persistence.*;
import lombok.Data;
import net.haloz.model.Color;

import java.time.LocalDate;

@Entity(name = "cat")
@Data
public class Cat {
    @Id
    @GeneratedValue
    private long id;
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

}

