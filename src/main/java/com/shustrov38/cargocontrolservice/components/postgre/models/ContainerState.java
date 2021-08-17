package com.shustrov38.cargocontrolservice.components.postgre.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Map;

@Entity
@Table(name = "container_state")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerState {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Instant time;

    @ElementCollection
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    @CollectionTable(
            name = "sensor_values",
            joinColumns = @JoinColumn(
                    name = "container_state_id",
                    referencedColumnName = "id",
                    nullable = false
            )
    )
    private Map<String, Double> sensorValues;
}
