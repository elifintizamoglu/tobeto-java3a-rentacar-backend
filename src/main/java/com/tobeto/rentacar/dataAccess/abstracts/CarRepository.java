package com.tobeto.rentacar.dataAccess.abstracts;

import com.tobeto.rentacar.entities.concretes.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<Car> findByPlateIgnoreCase(String plate);

    Car getById(int id);

    @Query("SELECT c FROM Car c " +
            "JOIN Model m ON m.id = c.model.id " +
            "JOIN Fuel f ON f.id = m.fuel.id " +
            "JOIN Transmission t ON t.id = m.transmission.id " +
            "JOIN Brand b ON b.id = m.brand.id " +
            "WHERE (:brandId IS NULL OR b.id = :brandId) " +
            "AND (:modelId IS NULL OR m.id = :modelId) " +
            "AND (:fuelId IS NULL OR f.id = :fuelId) " +
            "AND (:transmissionId IS NULL OR t.id = :transmissionId)")
    List<Car> getCarsByFilters(Integer brandId,
                               Integer modelId,
                               Integer fuelId,
                               Integer transmissionId);
}
