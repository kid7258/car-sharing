package study.carsharing.repository;

import study.carsharing.domain.Car;

import java.util.List;

public interface CarRepository {
    Car save(Car car);
    List<Car> findAll();
}
