package com.bootcamp.finalProject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "providers")
@NoArgsConstructor
@AllArgsConstructor
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_provider")
    private Long idProvider;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String country;
    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    private List<Part> parts;

    public Provider(String name, String address, String phone, String country) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return Objects.equals(idProvider, provider.idProvider) &&
                Objects.equals(name, provider.name) &&
                Objects.equals(address, provider.address) &&
                Objects.equals(phone, provider.phone) &&
                Objects.equals(country, provider.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProvider, name, address, phone, country);
    }
}
