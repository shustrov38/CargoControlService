package com.shustrov38.cargocontrolservice.components.postgre.repository;

import com.shustrov38.cargocontrolservice.components.postgre.models.EmulationData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface EmulationDataRepository extends PagingAndSortingRepository<EmulationData, Long> {

    @Query("select e from EmulationData e")
    List<EmulationData> getAll();

    @Query("select count(e) from EmulationData e")
    Integer getEmulationDataCount();
}
