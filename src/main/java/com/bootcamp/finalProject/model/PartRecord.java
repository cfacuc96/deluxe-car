package com.bootcamp.finalProject.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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
    @Column(nullable = false)
    @CreationTimestamp
    private Date createdAt;
    @Column(name = "normal_price", length = 6, nullable = false)
    private Double normalPrice;
    @Column(name = "sale_price", nullable = false)
    private Double salePrice;
    @Column(name = "urgent_price", length = 6, nullable = false)
    private Double urgentPrice;

    @JoinColumn(name = "id_part", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Part part;

    @JoinColumn(name = "id_discount_rate", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private DiscountRate discountRate;

    public PartRecord(Double normalPrice,
                      Double salePrice,
                      Double urgentPrice,
                      Part part,
                      DiscountRate discountRate) {
        this.normalPrice = normalPrice;
        this.salePrice = salePrice;
        this.urgentPrice = urgentPrice;
        this.part = part;
        this.discountRate = discountRate;
    }
}
