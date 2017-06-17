package tk.aizydorczyk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.aizydorczyk.model.dto.SummaryDto;
import tk.aizydorczyk.model.jpa.Car;
import tk.aizydorczyk.repository.CarRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.YEARS;

@Service
public class GarageService {

    @Autowired
    private CarRepository carRepository;

    public SummaryDto getCarSummary(){
        SummaryDto summaryDto = new SummaryDto();

        List<Car> allCars = carRepository.findAll();

        summaryDto.setValueOfCars(getValueOfCars(allCars));
        summaryDto.setRepairedCars(getNumberOfRepairedCars(allCars));
        summaryDto.setLentCars(getNumberOfLentCars(allCars));
        summaryDto.setNumberOfCars(getNumberOFCars(allCars));

        return summaryDto;
    }

    private Long getNumberOFCars(List<Car> allCars) {
        return allCars.stream().count();
    }

    private Long getNumberOfLentCars(List<Car> allCars) {
        return allCars
                .stream()
                .filter(Car::isLent)
                .count();
    }

    private Long getNumberOfRepairedCars(List<Car> allCars) {
        return allCars
                .stream()
                .filter(Car::getInRepair)
                .count();
    }

    private BigDecimal getValueOfCars(List<Car> allCars) {
        return allCars
                .stream()
                .map(car -> getActualPrice(car))
                .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, BigDecimal.ROUND_DOWN);
    }

    private BigDecimal getActualPrice(Car c){
        LocalDate dateOfPurchase = c.getDateOfPurchase();
        LocalDate now = LocalDate.now();

        long yearsBetween = YEARS.between(dateOfPurchase, now);

        if(yearsBetween > 0){
            BigDecimal changedPrice = c.getPrice().multiply(new BigDecimal("0.90"));

            for (int i = 0; i< yearsBetween - 1; i++){
                changedPrice = changedPrice.multiply(new BigDecimal("0.86"));
            }
            return changedPrice;

        } else {
            return c.getPrice();
        }
    }


    public void saveCar(Car car) {
        System.out.println(car);
        carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll()
                .stream()
                .peek(car -> car.setActualPrice(getActualPrice(car)))
                .collect(Collectors.toList());
    }
}
