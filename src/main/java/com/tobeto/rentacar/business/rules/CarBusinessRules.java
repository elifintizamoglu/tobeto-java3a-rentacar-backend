package com.tobeto.rentacar.business.rules;

import com.tobeto.rentacar.business.constants.CarMessages;
import com.tobeto.rentacar.core.utilities.exceptions.types.BusinessException;
import com.tobeto.rentacar.core.utilities.exceptions.types.ResourceNotFoundException;
import com.tobeto.rentacar.dataAccess.abstracts.CarRepository;
import com.tobeto.rentacar.entities.concretes.Car;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CarBusinessRules {

    CarRepository carRepository;

    public void carPlateCanNotBeDuplicated(String plate) {

        Optional<Car> car = carRepository.findByPlateIgnoreCase(plate);
        if (car.isPresent()) {
            throw new BusinessException(CarMessages.CarPlateAlreadyExists);
        }
    }

    public void isCarExists(int carId) {

        boolean isExists = carRepository.existsById(carId);
        if (!isExists) {
            throw new ResourceNotFoundException(CarMessages.CarNotFound);
        }
    }

    public double getCarDailyPrice(int carId) {

        Car car = carRepository.getById(carId);
        return car.getDailyPrice();
    }

}
