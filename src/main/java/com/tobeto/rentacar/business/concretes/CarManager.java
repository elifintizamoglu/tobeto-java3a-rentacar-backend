package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.CarService;
import com.tobeto.rentacar.business.constants.CarMessages;
import com.tobeto.rentacar.business.dtos.requests.car.CreateCarRequest;
import com.tobeto.rentacar.business.dtos.requests.car.UpdateCarRequest;
import com.tobeto.rentacar.business.dtos.responses.car.CreateCarResponse;
import com.tobeto.rentacar.business.dtos.responses.car.GetAllCarResponse;
import com.tobeto.rentacar.business.dtos.responses.car.GetCarByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.car.UpdateCarResponse;
import com.tobeto.rentacar.business.rules.CarBusinessRules;
import com.tobeto.rentacar.core.utilities.exceptions.types.ResourceNotFoundException;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.CarRepository;
import com.tobeto.rentacar.entities.concretes.Car;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarManager implements CarService {

    private CarRepository carRepository;
    private ModelMapperService modelMapperService;
    private CarBusinessRules carBusinessRules;

    @Override
    public CreateCarResponse addCar(CreateCarRequest createCarRequest) {

        carBusinessRules.carPlateCanNotBeDuplicated(createCarRequest.getPlate());

        Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);

        car.setId(0);
        car.setCreatedDate(LocalDateTime.now());
        Car createdCar = carRepository.save(car);

        CreateCarResponse createdCarResponse = this.modelMapperService.forResponse().map(createdCar, CreateCarResponse.class);
        return createdCarResponse;
    }

    @Override
    public List<GetAllCarResponse> getAllCars() {

        List<Car> cars = carRepository.findAll();

        List<GetAllCarResponse> response = cars.stream().map(car -> modelMapperService.forResponse()
                .map(car, GetAllCarResponse.class)).collect(Collectors.toList());
        return response;
    }

    @Override
    public void deleteCarById(int id) {

        carBusinessRules.isCarExists(id);
        carRepository.deleteById(id);
    }

    @Override
    public UpdateCarResponse updateCarById(int id, UpdateCarRequest updateCarRequest) {

        Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CarMessages.CarNotFound));

        carBusinessRules.carPlateCanNotBeDuplicated(updateCarRequest.getPlate());
        Car updatedCar = modelMapperService.forRequest().map(updateCarRequest, Car.class);

        car.setModelYear(updatedCar.getModelYear());
        car.setPlate(updatedCar.getPlate());
        car.setState(updatedCar.getState());
        car.setDailyPrice(updatedCar.getDailyPrice());
        car.setModel(updatedCar.getModel());
        car.setUpdatedDate(LocalDateTime.now());
        carRepository.save(car);

        UpdateCarResponse response = modelMapperService.forResponse().map(car, UpdateCarResponse.class);
        return response;
    }

    @Override
    public GetCarByIdResponse getCarById(int id) {

        Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CarMessages.CarNotFound));

        GetCarByIdResponse response = modelMapperService.forResponse().map(car, GetCarByIdResponse.class);
        return response;
    }
}
