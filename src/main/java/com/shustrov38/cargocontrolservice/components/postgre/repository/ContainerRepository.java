package com.shustrov38.cargocontrolservice.components.postgre.repository;

import com.shustrov38.cargocontrolservice.components.postgre.models.Container;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ContainerRepository extends PagingAndSortingRepository<Container, Long> {
}
