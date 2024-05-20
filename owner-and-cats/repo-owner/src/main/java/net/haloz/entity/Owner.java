package net.haloz.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Entity(name = "owner")
@Data
public class Owner {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private LocalDate birth;
    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Cat cats;

    public Owner() {
    }
}
