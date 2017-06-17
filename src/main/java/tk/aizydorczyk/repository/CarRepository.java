package tk.aizydorczyk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.aizydorczyk.model.jpa.Car;

public interface CarRepository extends JpaRepository<Car,Long> {

}
