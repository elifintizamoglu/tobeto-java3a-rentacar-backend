package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.CarService;
import com.tobeto.rentacar.business.dtos.requests.car.CreateCarRequest;
import com.tobeto.rentacar.business.dtos.requests.car.UpdateCarRequest;
import com.tobeto.rentacar.business.dtos.responses.car.CreateCarResponse;
import com.tobeto.rentacar.business.dtos.responses.car.GetAllCarResponse;
import com.tobeto.rentacar.business.dtos.responses.car.GetCarByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.car.UpdateCarResponse;
import com.tobeto.rentacar.business.rules.CarBusinessRules;
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


        Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
        car.setCreatedDate(LocalDateTime.now());
        car.setId(0);
        Car createdCar = this.carRepository.save(car);

        CreateCarResponse createdCarResponse = this.modelMapperService.forResponse()
                .map(createdCar, CreateCarResponse.class);
        return createdCarResponse;
    }

    @Override
    public List<GetAllCarResponse> getAllCars() {

        var result = carRepository.findAll();
        List<GetAllCarResponse> response = result.stream()
                .map(car -> modelMapperService.forResponse()
                        .map(car, GetAllCarResponse.class)).collect(Collectors.toList());

        return response;
    }

    @Override
    public void deleteCarById(int id) {
        Car car = carRepository.findById(id).orElseThrow(()-> new RuntimeException("There is no car with this id!"));
        car.setDeletedDate(LocalDateTime.now());
        carRepository.delete(car);
    }

    @Override
    public UpdateCarResponse updateCarById(int id, UpdateCarRequest updateCarRequest) {
        Car car = carRepository.findById(id).orElseThrow(()-> new RuntimeException("There is no car with this id!"));
        Car updatedCar = modelMapperService.forRequest().map(updateCarRequest, Car.class);

        car.setUpdatedDate(LocalDateTime.now());
        car.setModelYear(updatedCar.getModelYear() != 0 ? updatedCar.getModelYear() : car.getModelYear());
        car.setPlate(updatedCar.getPlate() != null ? updatedCar.getPlate() : car.getPlate());
        car.setState(updatedCar.getState() != 0 ? updatedCar.getState() : car.getState());
        car.setDailyPrice(updatedCar.getDailyPrice() != 0 ? updatedCar.getDailyPrice() : car.getDailyPrice());
        car.setModel(updatedCar.getModel() != null ? updatedCar.getModel() : car.getModel());

        carRepository.save(car);

        UpdateCarResponse response = modelMapperService.forResponse().map(car, UpdateCarResponse.class);
        return response;
    }

    @Override
    public GetCarByIdResponse getCarById(int id) {
        Car car = carRepository.findById(id).orElseThrow(()-> new RuntimeException("There is no car with this id!"));
        GetCarByIdResponse response = modelMapperService.forResponse().map(car, GetCarByIdResponse.class);
        return response;
    }
}
