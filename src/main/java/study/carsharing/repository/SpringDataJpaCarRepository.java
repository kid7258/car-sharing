package study.carsharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.carsharing.domain.Car;

public interface SpringDataJpaCarRepository extends JpaRepository<Car, Long>, CarRepository {

}
