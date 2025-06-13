package ua.opnu.practice1kr.Car;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public Car getById(Long id) {
        return carRepository.findById(id).orElseThrow();
    }

    public List<Car> getByClientId(Long clientId) {
        return carRepository.findByClientId(clientId);
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public Car update(Long id, Car updatedCar) {
        Car car = getById(id);
        car.setMake(updatedCar.getMake());
        car.setModel(updatedCar.getModel());
        car.setYear(updatedCar.getYear());
        car.setVin(updatedCar.getVin());
        return carRepository.save(car);
    }

    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}

