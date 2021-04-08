package com.bootcamp.finalProject.model;

import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @Column(name = "part_code", length = 8)
    private Integer partCode;
    @Column(length = 100)
    private String description;
    @Column(length = 4)
    private Integer widthDimension;
    @Column(length = 4)
    private Integer tallDimension;
    @Column(length = 4)
    private Integer longDimension;
    @Column(length = 5)
    private Integer netWeight;
    private Integer quantity;
    private Date lastModification; //Se encarga de guardar la fecha de la ultima modificacion tanto de precio como un atributo

    @OrderBy("created_at DESC") //Trae la lista de partRecords ordenada por fecha de createAt (Primero trae la ultima modificacion agregada)
    @OneToMany(mappedBy = "part", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<PartRecord> partRecords; //historial de precios que tuvo el producto

    @JoinColumn(name = "id_provider", nullable = false)
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JsonBackReference
    private Provider provider;
}
