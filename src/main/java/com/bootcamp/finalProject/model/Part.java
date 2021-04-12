package com.bootcamp.finalProject.model;

import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "parts")
@AllArgsConstructor
@NoArgsConstructor
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_part")
    private Long idPart;
    @Column(name = "part_code", nullable = false, length = 8)
    private Integer partCode;
    @Column(length = 100,nullable = false)
    private String description;
    @Column(length = 4, nullable = false)
    private Integer widthDimension;
    @Column(length = 4, nullable = false)
    private Integer tallDimension;
    @Column(length = 4, nullable = false)
    private Integer longDimension;
    @Column(length = 5, nullable = false)
    private Integer netWeight;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Date lastModification; //Se encarga de guardar la fecha de la ultima modificacion tanto de precio como un atributo

    @OrderBy("created_at DESC") //Trae la lista de partRecords ordenada por fecha de createAt (Primero trae la ultima modificacion agregada)
    @OneToMany(mappedBy = "part", cascade = CascadeType.ALL)
    private List<PartRecord> partRecords; //historial de precios que tuvo el producto

    @JoinColumn(name = "id_provider", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private Provider provider;

    @OneToMany(mappedBy = "partOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return Objects.equals(idPart, part.idPart) && Objects.equals(partCode, part.partCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPart, partCode);
    }
}
