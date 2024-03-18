package com.tobeto.rentacar.dataAccess.abstracts;

import com.tobeto.rentacar.entities.concretes.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<Car> findByPlateIgnoreCase(String plate);
}
