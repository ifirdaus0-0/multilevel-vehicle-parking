package com.parking.vehicleparking.service;

import com.parking.vehicleparking.domain.Car;
import com.parking.vehicleparking.domain.ParkingLot;
import com.parking.vehicleparking.repository.CarRepository;
import com.parking.vehicleparking.repository.ParkingLotRepo;
import com.parking.vehicleparking.service.util.ParkingLotManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ParkingLotServiceTest {

    @Mock
    CarRepository carRepository;

    @Mock
    ParkingLotRepo parkingLotRepo;

    @InjectMocks
    private ParkingLotService mockparkingLotService;

    @BeforeEach
    void setup() {

    }

    @Test
    public void testParkCar(){
        List<ParkingLot> parkingLot = List.of(new ParkingLot(5));
        Mockito.when(parkingLotRepo.findAll()).thenReturn(parkingLot);
        List<Car> allList = List.of(new Car("KA 01 HH 1234","White",1),
                new Car("KA 01 HH 1235","White",3));
        Mockito.when(mockparkingLotService.getAllCars()).thenReturn(allList);
        String slot = mockparkingLotService.parkCar("KA 01 HH 9999","Blue");
        Assertions.assertEquals("Allocated slot number: 2",slot);
    }

    @Test
    public void testGetSlotByColor(){
       List<ParkingLot> parkingLot = List.of(new ParkingLot(5));
        List<Car> allList = List.of(new Car("KA 01 HH 1234","White",2),
                new Car("KA 01 HH 1235","White",3));
        Mockito.when(mockparkingLotService.getAllCars()).thenReturn(allList);
        List<Integer> slots = mockparkingLotService.getSlotByColor("White");
        Assertions.assertTrue(slots.contains(2));
    }

    @Test
    public void testVacateParkingSlot(){
       List<ParkingLot> parkingLot = List.of(new ParkingLot(5));
       Mockito.when(parkingLotRepo.findAll()).thenReturn(parkingLot);
        List<Car> allList = List.of(new Car("KA 01 HH 1234","White",2),
                new Car("KA 01 HH 1235","White",3));
        Mockito.when(mockparkingLotService.getAllCars()).thenReturn(allList);
        String msg = mockparkingLotService.vacateParkingSlot(2);
        Assertions.assertEquals("Slot number 2 is free",msg);
    }
    @Test
    public void testGetRegNoByColor(){
        List<ParkingLot> parkingLot = List.of(new ParkingLot(5));
        List<Car> allList = List.of(new Car("KA 01 HH 1234","White",2),
                new Car("KA 01 HH 1235","White",3));
        Mockito.when(mockparkingLotService.getAllCars()).thenReturn(allList);
        List<String> regNo = mockparkingLotService.getRegNoByColor("White");
        Assertions.assertTrue(regNo.contains("KA 01 HH 1235"));
    }

    @Test
    public void testParkingLotStatus(){

        List<ParkingLot> parkingLot = List.of(new ParkingLot(5));
        //Mockito.when(parkingLotRepo.findAll()).thenReturn(parkingLot);
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("KA 01 HH 1235","White",3));
        carList.add(new Car("KA 01 HH 1234","White",2));
        Mockito.when(mockparkingLotService.getAllCars()).thenReturn(carList);
        String status = mockparkingLotService.parkingLotStatus();
        Assertions.assertTrue(status.indexOf("KA 01 HH 1234")!=-1);

    }

}
