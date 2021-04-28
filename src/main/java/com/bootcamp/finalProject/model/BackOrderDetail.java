package com.bootcamp.finalProject.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "backOrderDetails")
public class BackOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_back_order_detail")
    private Long idOrderDetail;

    @Column(nullable = false, length = 1)
    private String accountType;
    @Column(nullable = false)
    private Integer quantity;

    @OneToOne(mappedBy = "backOrderDetail")
    private BackOrder backOrder;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_part", nullable = false)
    @JsonBackReference
    private Part partBackOrder;

    public BackOrderDetail(String accountType, Integer quantity, Part partBackOrder, BackOrder backOrder) {
        this.accountType = accountType;
        this.quantity = quantity;
        this.partBackOrder = partBackOrder;
        this.backOrder = backOrder;
    }
}
