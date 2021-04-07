package com.bootcamp.finalProject.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "discount_rates")
public class DiscountRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_discount_rate")
    private Long idDiscountRate;
    private String description;
    private String discount;
    @OneToMany(mappedBy = "discountRate", cascade = CascadeType.PERSIST)
    private List<PartRecord> partRecords;
}
