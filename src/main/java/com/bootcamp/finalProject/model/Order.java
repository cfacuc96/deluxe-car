package com.bootcamp.finalProject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(idOrder, order.idOrder) && Objects.equals(orderDate, order.orderDate) && Objects.equals(deliveryDate, order.deliveryDate) && Objects.equals(deliveredDate.getDate(), order.deliveredDate.getDate()) && Objects.equals(deliveryStatus, order.deliveryStatus) && Objects.equals(orderDetails, order.orderDetails) && Objects.equals(subsidiary, order.subsidiary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, orderDate, deliveryDate, deliveredDate, deliveryStatus, orderDetails, subsidiary);
    }

    @Id
    @Column(name = "id_order", length = 8)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;
    @Column(nullable = false)
    private Date orderDate;
    @Column(nullable = false)
    private Date deliveryDate;
    @Column(nullable = true)
    private Date deliveredDate;
    @Column(nullable = false, length = 1)
    private String deliveryStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference
    @JoinColumn(name = "id_subsidiary", nullable = false)
    private Subsidiary subsidiary;




}
