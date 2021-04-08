package com.bootcamp.finalProject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "providers")
@NoArgsConstructor
@AllArgsConstructor
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_provider")
    private Long idProvider;
    private String name;
    private String address;
    private String phone;
    private String country;
    @OneToMany(mappedBy = "provider", cascade = CascadeType.PERSIST)
    private List<Part> parts;
}
