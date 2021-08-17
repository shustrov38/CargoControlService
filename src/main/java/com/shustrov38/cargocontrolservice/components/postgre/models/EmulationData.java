package com.shustrov38.cargocontrolservice.components.postgre.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "emulation_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmulationData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @OneToMany(
            cascade = CascadeType.ALL,
            targetEntity = Container.class
    )
    @JoinColumn(
            name = "emulation_data_id",
            referencedColumnName = "id",
            nullable = false
    )
    private List<Container> containers;
}
