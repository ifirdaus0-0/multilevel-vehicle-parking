package com.parking.vehicleparking.service.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.parking.vehicleparking.domain.Car;

import lombok.Getter;
@Getter
public class ParkingLotManager {

	private int parkigLotMaxCapacity;
	private int parkigLotCurrCapacity;
	private Set<Car> parkedCars = new HashSet<>();
	private Car[] slots;

	private static ParkingLotManager parkingLotManager = null;
	private ParkingLotManager() {};
	public static ParkingLotManager getManager() {
		if(parkingLotManager==null) {
			parkingLotManager = new ParkingLotManager();
		}
		return parkingLotManager;
	}
	
	public boolean isFull() {
		return parkigLotCurrCapacity==parkigLotMaxCapacity;
	}

	
    public void initializeParkingLot(int capacity, List<Car> cars) {
    	if(capacity<=0) throw new IllegalArgumentException("Please create parking lot");
    	this.parkigLotMaxCapacity = capacity;
    	if(parkedCars==null || parkedCars.isEmpty()) {
			slots = new Car[parkigLotMaxCapacity];
			parkedCars = new HashSet<>();
		}
    	if(cars!=null && !cars.isEmpty() && parkedCars.isEmpty()) {
    		this.parkigLotCurrCapacity = cars.size();
			//initialize parkedCars
			for(Car car : cars) {
				parkedCars.add(car);
			}
			//initialize parking slot
			for(Car car : cars) {
				slots[car.getSlotNo()-1]  = car;
			}
    	}
    }
	public int parkCar(Car car) {
		if(isFull()) return -1;
		if(parkigLotMaxCapacity==0) return -2;
		if(parkedCars.contains(car)) return 0;
		for(int i=0; i<parkigLotMaxCapacity; i++) {
			if(slots[i]==null) {
				car.setSlotNo(i+1);
				slots[i]=car;
				parkedCars.add(car);
				parkigLotCurrCapacity++;
				return i+1;
			}
		}
		return -1;
	}
	
	public void vacateParkingSlot(Car car,int slotNo) {
		slots[car.getSlotNo() - 1] = null;
		parkedCars.remove(car);
		parkigLotCurrCapacity--;
	}
}
