package com.bootcamp.finalProject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "parts")
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
    @JoinColumn(name = "id_provider", nullable = false)
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Provider provider;
    private Integer quantity;
    @OneToMany(mappedBy = "part", cascade = CascadeType.PERSIST)
    private List<PartRecord> partRecords;
    private Date lastModification;
}
