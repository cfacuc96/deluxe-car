package com.bootcamp.finalProject.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "discount_rates")
@NoArgsConstructor
@AllArgsConstructor
public class DiscountRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_discount_rate")
    private Long idDiscountRate;
    @Column(nullable=false)
    private String description;
    @Column(nullable=false)
    private String discount;
    @OneToMany(mappedBy = "discountRate", cascade = CascadeType.ALL)
    private List<PartRecord> partRecords;

    public DiscountRate(String description, String discount) {
        this.description = description;
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountRate that = (DiscountRate) o;
        return Objects.equals(idDiscountRate, that.idDiscountRate) && Objects.equals(description, that.description) && Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDiscountRate, description, discount);
    }
}
