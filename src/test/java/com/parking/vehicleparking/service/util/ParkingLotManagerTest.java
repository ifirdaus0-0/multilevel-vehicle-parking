package com.parking.vehicleparking.service.util;

import com.parking.vehicleparking.domain.Car;
import com.parking.vehicleparking.domain.ParkingLot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotManagerTest {


    private ParkingLotManager mockmanager = ParkingLotManager.getManager();

    @Test
    public void testIsFull(){

        mockmanager.initializeParkingLot(5,null);
        for(int i=0; i<5; i++){
            mockmanager.parkCar(new Car("KA"+i,"White"+i,i));
        }
        Assertions.assertEquals(true,mockmanager.isFull());
    }

    @Test
    public void testInitializeParkingLot(){
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("KA01HH123","Blue",1));
        carList.add(new Car("KAo1HH2345","Red",3));
        mockmanager.initializeParkingLot(5,carList);
        Assertions.assertEquals(5,ParkingLotManager.getManager().getParkigLotMaxCapacity());
        Assertions.assertTrue(mockmanager.getParkedCars().contains(new Car("KA01HH123","Blue",1)));

    }

    @Test
    public void testParkedcar(){
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("KA01HH123","Blue",1));
        carList.add(new Car("KAo1HH2345","Red",3));
        mockmanager.initializeParkingLot(5,carList);
        Car car = new Car("KA01HH6789","White",-1);
        Assertions.assertEquals(2, mockmanager.parkCar(car));
        Assertions.assertEquals(0,mockmanager.parkCar(car));
    }

    @Test
    public void testVacateCar(){
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("KA01HH123","Blue",1));
        carList.add(new Car("KAo1HH2345","Red",3));
        mockmanager.initializeParkingLot(5,carList);
        mockmanager.vacateParkingSlot(new Car("KAo1HH2345","Red",3),3);
        Car[] slots = mockmanager.getSlots();
        Assertions.assertTrue(slots[2]==null);
        Assertions.assertFalse(mockmanager.getParkedCars().contains(new Car("KAo1HH2345","Red",3)));
        Assertions.assertEquals(1,mockmanager.getParkigLotCurrCapacity());
    }


}
