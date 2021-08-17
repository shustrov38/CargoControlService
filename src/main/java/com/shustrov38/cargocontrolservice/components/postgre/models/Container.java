package com.shustrov38.cargocontrolservice.components.postgre.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "container")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Container {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(
            cascade = CascadeType.ALL,
            targetEntity = ContainerState.class
    )
    @JoinColumn(
            name = "container_id",
            referencedColumnName = "id",
            nullable = false
    )
    private List<ContainerState> state;
}
