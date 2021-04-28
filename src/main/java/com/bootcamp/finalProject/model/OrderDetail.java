package com.bootcamp.finalProject.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orderDetails")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_detail")
    private Long idOrderDetail;

    @Column(nullable = false, length = 1)
    private String accountType;
    @Column(nullable = false)
    private Integer quantity;
    @Column(length = 100)
    private String reason;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_part", nullable = false)
    @JsonBackReference
    private Part partOrder;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_order", nullable = false)
    @JsonBackReference
    private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail that = (OrderDetail) o;
        return Objects.equals(idOrderDetail, that.idOrderDetail) && Objects.equals(accountType, that.accountType) && Objects.equals(quantity, that.quantity) && Objects.equals(reason, that.reason) && Objects.equals(partOrder, that.partOrder) && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrderDetail, accountType, quantity, reason, partOrder, order);
    }
}
