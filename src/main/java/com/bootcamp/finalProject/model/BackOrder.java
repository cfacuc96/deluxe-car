package com.bootcamp.finalProject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Locale;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "back_orders")
public class BackOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_back_order", length = 8)
    private Long idBackOrder;

    @Column(nullable = false)
    private Date backOrderDate;
    private Date finishBackOrderDate;
    @Column(length = 1, nullable = false)
    private String backOrderStatus;
    @Column(length = 1, nullable = false)
    private String backOrderPriority;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_back_order_detail", referencedColumnName = "id_back_order_detail")
    private BackOrderDetail backOrderDetail;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "id_subsidiary", nullable = false)
    private Subsidiary subsidiary;

    public BackOrder(Date backOrderDate, String backOrderStatus, String backOrderPriority, Subsidiary subsidiary) {
        this.backOrderDate = backOrderDate;
        this.backOrderStatus = backOrderStatus;
        this.backOrderPriority = backOrderPriority.toUpperCase(Locale.ROOT);
        this.subsidiary = subsidiary;
    }
}
