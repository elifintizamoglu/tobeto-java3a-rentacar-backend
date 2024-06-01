package com.tobeto.rentacar.dataAccess.abstracts;

import com.tobeto.rentacar.entities.concretes.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

    @Query("SELECT r FROM Rental r " +
            "WHERE r.car.id = :carId " +
            "AND r.id != :rentalId " +
            "AND r.startDate <= :endDate " +
            "AND r.endDate >= :startDate")
    List<Rental> findByCarIdAndDateRange(int rentalId, int carId, LocalDate startDate, LocalDate endDate);
}


