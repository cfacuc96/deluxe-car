package com.bootcamp.finalProject.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartRecord that = (PartRecord) o;
        return Objects.equals(idPartRecord, that.idPartRecord) &&
                Objects.equals(normalPrice, that.normalPrice) &&
                Objects.equals(salePrice, that.salePrice) &&
                Objects.equals(urgentPrice, that.urgentPrice) &&
                Objects.equals(part, that.part) &&
                Objects.equals(discountRate, that.discountRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPartRecord, createdAt, normalPrice, salePrice, urgentPrice, part, discountRate);
    }

    @Override
    public String toString() {
        return "PartRecord{" +
                "idPartRecord=" + idPartRecord +
                ", createdAt=" + createdAt +
                ", normalPrice=" + normalPrice +
                ", salePrice=" + salePrice +
                ", urgentPrice=" + urgentPrice +
                ", part=" + part +
                ", discountRate=" + discountRate +
                '}';
    }
}
