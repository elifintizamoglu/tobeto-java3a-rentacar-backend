package com.tobeto.rentacar.dataAccess.abstracts;

import com.tobeto.rentacar.entities.concretes.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Integer> {

    @Query("SELECT m FROM Model m " +
            "WHERE m.id != :modelId " +
            "AND m.brand.id = :brandId " +
            "AND LOWER(m.name) = LOWER(:name)")
    Optional<Model> findByNameAndBrandIdIgnoreCase(int modelId, int brandId, String name);

    List<Model> getModelsByBrandId(int id);

    List<Model> findAllByOrderByBrandNameAscNameAsc();
}
