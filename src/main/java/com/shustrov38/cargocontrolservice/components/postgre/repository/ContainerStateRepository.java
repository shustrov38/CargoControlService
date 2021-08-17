package com.shustrov38.cargocontrolservice.components.postgre.repository;

import com.shustrov38.cargocontrolservice.components.postgre.models.ContainerState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ContainerStateRepository extends PagingAndSortingRepository<ContainerState, Long> {
}
