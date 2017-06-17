package tk.aizydorczyk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.aizydorczyk.model.dto.SummaryDto;
import tk.aizydorczyk.model.jpa.Car;
import tk.aizydorczyk.service.GarageService;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

@RestController
public class GarageController {

    @Autowired
    private GarageService garageService;

    @RequestMapping(value = "/summary")
    public SummaryDto getSummary(){
        return garageService.getCarSummary();
    }

    @RequestMapping(value = "/car", method = RequestMethod.POST)
    public void addCar(@RequestBody Car car){
        garageService.saveCar(car);
    }

    @RequestMapping(value = "/car", method = RequestMethod.GET)
    public ResponseEntity<List<Car>> getAllCars(){
        return ResponseEntity.ok(garageService.getAllCars());
    }
}
