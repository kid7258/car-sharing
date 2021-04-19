package study.carsharing.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import study.carsharing.domain.Car;
import study.carsharing.service.CarService;
import study.carsharing.util.FileUploadUtil;

import java.io.IOException;

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
    public ResponseEntity save(
            @RequestParam("model") String model,
            @RequestParam("number") String number,
            @RequestParam("engine") String engine,
            @RequestParam("parking") String parking,
            @RequestParam("quantity") String quantity,
            @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        Car car = new Car();
        car.setModel(model);
        car.setNumber(number);
        car.setEngine(engine);
        car.setParking(parking);
        car.setQuantity(quantity);
        car.setImage(fileName);

        Car savedCar = carService.save(car);

        String uploadDir = "photos/" + savedCar.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return ResponseEntity.ok().body(savedCar);
    }
}
