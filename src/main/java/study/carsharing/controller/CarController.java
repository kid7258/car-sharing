package study.carsharing.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.carsharing.domain.Car;
import study.carsharing.service.CarService;

@RestController("/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok().body(carService.findAll());
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Car car) {
        return ResponseEntity.ok().body(carService.save(car));
    }
}
