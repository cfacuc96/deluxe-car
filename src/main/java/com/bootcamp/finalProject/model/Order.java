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

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

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
