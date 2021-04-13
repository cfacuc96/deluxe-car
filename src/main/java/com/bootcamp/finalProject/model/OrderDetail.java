package com.bootcamp.finalProject.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
}
