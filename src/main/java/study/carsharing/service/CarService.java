package study.carsharing.service;

import study.carsharing.domain.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();
    Car save(Car car);
}
