package com.tobeto.rentacar.dataAccess.abstracts;

import com.tobeto.rentacar.business.dtos.responses.car.GetCarsByFiltersResponse;
import com.tobeto.rentacar.entities.concretes.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<Car> findByPlateIgnoreCase(String plate);

    Car getById(int id);

    @Query("SELECT new com.tobeto.rentacar.business.dtos.responses.car.GetCarsByFiltersResponse(c.id, c.modelYear, c.plate, c.state, c.dailyPrice, " +
            "b.name, m.name, f.name, t.name) " +
            "FROM Car c " +
            "JOIN Model m ON m.id = c.model.id " +
            "JOIN Fuel f ON f.id = m.fuel.id " +
            "JOIN Transmission t ON t.id = m.transmission.id " +
            "JOIN Brand b ON b.id = m.brand.id " +
            "WHERE (:brandId IS NULL OR b.id = :brandId) " +
            "AND (:modelId IS NULL OR m.id = :modelId) " +
            "AND (:fuelId IS NULL OR f.id = :fuelId) " +
            "AND (:transmissionId IS NULL OR t.id = :transmissionId)")
    List<GetCarsByFiltersResponse> getCarsByFilters(@Param("brandId") Integer brandId,
                                                    @Param("modelId") Integer modelId,
                                                    @Param("fuelId") Integer fuelId,
                                                    @Param("transmissionId") Integer transmissionId);
}
