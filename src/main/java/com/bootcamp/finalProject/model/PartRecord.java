package com.bootcamp.finalProject.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "part_records")
@AllArgsConstructor
@NoArgsConstructor
public class PartRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_part_record")
    private Long idPartRecord;
    private Date createdAt;
    @Column(name = "normal_price", length = 6)
    private Double normalPrice;
    @Column(name = "sale_price")
    private Double salePrice;
    @Column(name = "urgent_price", length = 6)
    private Double urgentPrice;

    @JoinColumn(name = "id_part", nullable = false)
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Part part;

    @JoinColumn(name = "id_discount_rate", nullable = false)
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference
    private DiscountRate discountRate;
}
