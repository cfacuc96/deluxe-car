package com.bootcamp.finalProject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subsidiaries")
public class Subsidiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subsidiary", length = 4)
    private Long idSubsidiary;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String country;

    @OneToMany(mappedBy = "subsidiary", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
   // @OrderColumn(name = "order_index")
    private List<Order> orders;

    @OneToMany(mappedBy = "subsidiary", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<SubsidiaryStock> subsidiaryStocks;

    @OneToMany(mappedBy = "subsidiary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;
}
