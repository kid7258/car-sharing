package study.carsharing.controller;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.carsharing.domain.Car;
import study.carsharing.service.CarService;

@RestController
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public ResponseEntity findAll() {
        return ResponseEntity.ok().body(carService.findAll());
    }

    @PostMapping("/cars")
    public ResponseEntity save(@RequestBody Car car) {
        return ResponseEntity.ok().body(carService.save(car));
    }
}
