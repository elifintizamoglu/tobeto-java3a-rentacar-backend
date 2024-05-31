package com.tobeto.rentacar.entities.concretes;

import com.tobeto.rentacar.core.entities.BaseEntity;
import com.tobeto.rentacar.entities.concretes.enums.CarState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    @Column(name = "modelYear")
    private int modelYear;

    @Column(name = "plate")
    private String plate;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private CarState state;

    @Column(name = "dailyPrice")
    private double dailyPrice;

    @Column(name = "imagePath")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "modelId")
    private Model model;

    @OneToMany(mappedBy = "car")
    private List<Rental> rentals;

}
