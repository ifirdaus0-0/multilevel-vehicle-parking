package com.parking.vehicleparking.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators.In;
import org.springframework.stereotype.Service;

import com.mongodb.client.model.Collation;
import com.mongodb.client.model.CollationStrength;
import com.parking.vehicleparking.domain.Car;
import com.parking.vehicleparking.domain.ParkingLot;
import com.parking.vehicleparking.repository.CarRepository;
import com.parking.vehicleparking.repository.ParkingLotRepo;
import com.parking.vehicleparking.service.util.ParkingLotManager;



@Service
public class ParkingLotService {

	@Autowired
	ParkingLotRepo parkingLotRepository;
	@Autowired
	CarRepository carRepository;
	
	public String createParkingLot(int numberOfSlots) {
		int capacity = getParkingLotData();
		if(capacity!=-1) {
			ParkingLotManager.getManager().initializeParkingLotcapacity(capacity);
			return "Parking Lot is Already created with Capacity = " + capacity;
		}
		ParkingLot result = parkingLotRepository.save(new ParkingLot(numberOfSlots));
		if(result!=null) {
			//initialize parking lot manager
			ParkingLotManager.getManager().createParkingLot(numberOfSlots);
			return String.format("Created a parking lot with %d slots", numberOfSlots);
		}
		else return "error";
	}
	
	
	public int getParkingLotData() {
		List<ParkingLot> list = parkingLotRepository.findAll();
		if(list!=null && !list.isEmpty()) return list.iterator().next().getMaxCapacity();
		return -1;
	}
	
	
	
	
	public List<Car> getAllCars() {
		return carRepository.findAll();
	}
	
	
	
	public String parkCar(String carnumber, String color) {
		if(ParkingLotManager.getManager().getParkedCars()==null || ParkingLotManager.getManager().getParkedCars().isEmpty()) {
			ParkingLotManager.getManager().initialiseParkingLot(getParkingLotData(), getAllCars());
		}
		Car car = new Car();
		car.setCarnumber(carnumber);
		car.setColor(color);
		
		int slot = ParkingLotManager.getManager().parkCar(car);
		if(slot==0) return "This car is already parked";
		else if(slot==-1) return "Sorry All slots are full";
		else if(slot==-2) return "Parking lot is not initialised";
		carRepository.save(car);
		return "Allocated slot number: " + slot;
	}
	
	public List<String> getRegNoByColor(String color) {
		List<String> result = new ArrayList<>();
		List<Car> allCars  = getAllCars();
		if(allCars!=null) {
			for (Car car : allCars) {
				if (car.getColor().equals(color)) {
						result.add(car.getCarnumber());
				}
			}
			
		}
		return result;
	}
	
	public List<Integer> getSlotNoByRegNo(String regNo) {
		List<Integer> result = new ArrayList<>();
		List<Car> allCars  = getAllCars();
		if(allCars!=null) {
			for (Car car : allCars) {
				if (car.getCarnumber().equals(regNo)) {
						result.add(car.getSlotNo());
				}
			}
			
		}
		return result;
	}
	
	public List<Integer> getSlotByColor(String color) {
		List<Integer> result = new ArrayList<>();
		List<Car> allCars  = getAllCars();
		if(allCars!=null) {
			for (Car car : allCars) {
				if (car.getColor().equals(color)) {
						result.add(car.getSlotNo());
				}
			}
			
		}
		return result;
	}
	
	public String vacateParkingSlot(int slotNo) {
		List<Car> allCars = getAllCars();
		if(ParkingLotManager.getManager().getParkedCars()==null || ParkingLotManager.getManager().getParkedCars().isEmpty()) {
			ParkingLotManager.getManager().initialiseParkingLot(getParkingLotData(), allCars);
		}
		
		for(Car car : allCars) {
			if(car.getSlotNo()==slotNo) {
				ParkingLotManager.getManager().vacateParkingSlot(slotNo);
				carRepository.delete(car);
				return String.format("Slot number %d is free", slotNo);
			}
		}
		
		return String.format("Slot no %d is already free", slotNo);
	}
	
	public String parkingLotStatus() {
		List<Car> allCarsList = getAllCars();
		Collections.sort(allCarsList,(c1,c2)->c1.getSlotNo()-c2.getSlotNo());
		
		if(allCarsList==null || allCarsList.isEmpty()) return "There is no Car in Parking Lot";
		StringBuffer allStatus = new StringBuffer("Slot No.| Registration No| Colour \n");
		int i=1;
		for(Car car : allCarsList) {
			String carInfo = i +".   "+car.getSlotNo()+"     "+ car.getCarnumber()+"     "+car.getColor()+"\n";
			allStatus.append(carInfo);
			i++;
		}
		
		return allStatus.toString();
	}
	


}