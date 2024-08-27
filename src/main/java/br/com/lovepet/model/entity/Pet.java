package br.com.lovepet.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import br.com.lovepet.model.entity.Usuario;

@Data
@Entity(name = "tb_pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String age;
    private String description;
    private String species;
    private String breed;
    private String color;
    private boolean available;
    private LocalDateTime register;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private br.com.lovepet.model.entity.Usuario user;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Usuario adopter;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "pet_id")
    private List<Image> images;
}
