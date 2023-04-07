package com.parking.vehicleparking.service.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.parking.vehicleparking.domain.Car;

import lombok.Getter;

public class ParkingLotManager {
	
	private int parkigLotMaxCapacity;
	private int parkigLotCurrCapacity;
	
	@Getter
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
	

	public boolean createParkingLot(int maxCapacity) {
		  initializeParkingLotcapacity(maxCapacity);
		  parkedCars = new HashSet<>();
		  slots = new Car[parkigLotMaxCapacity];
		 return true;
	}
	
	public boolean isFull() {
		return parkigLotCurrCapacity==parkigLotMaxCapacity;
	}
	
	public void initializeParkingLotcapacity(int maxCapacity) {
		this.parkigLotMaxCapacity = maxCapacity;
	}
	
	public void intialiseParkingLotWithCars(List<Car> savedCars) {
		if(savedCars!=null &&!savedCars.isEmpty() ) {
			//initialize parkedCars
			Set<Car> parkedCars = new HashSet<>();
			for(Car car : savedCars) {
				parkedCars.add(car);
			}
			this.parkedCars = parkedCars;
			
			//initialize parking slot 
			Car[] parkedSlots =  new Car[parkigLotMaxCapacity];
			for(Car car : savedCars) {
				parkedSlots[car.getSlotNo()-1]  = car;
			}
			this.slots = parkedSlots;
		}
		
	}
	
	
	
    public void initializeParkingLot(int capacity, List<Car> cars) {
    	this.parkigLotMaxCapacity = capacity;
    	if(parkigLotMaxCapacity>0) {
    		slots= new Car[parkigLotMaxCapacity];
    		parkedCars = new HashSet<>();
    	}
    	if(cars!=null) {
    		this.parkigLotCurrCapacity = cars.size();
    		if(!cars.isEmpty()) {
    			intialiseParkingLotWithCars(cars);
    		}
    		
    	}
    }
	public int parkCar(Car car) {
		if(isFull()) return -1;
		if(parkigLotMaxCapacity==-1) return -2;
		for(int i=0; i<parkigLotMaxCapacity; i++) {
			if(parkedCars.contains(car)) return 0;
			else {
			if(slots[i]==null) {
				car.setSlotNo(i+1);
				slots[i]=car;
				parkedCars.add(car);
				parkigLotCurrCapacity++;
				return i+1;
			}
		   }
		}
		return -1;
	}
	
	public void vacateParkingSlot(int slotNo) {
		for(Car car : slots) {
			if(car!=null && car.getSlotNo()==slotNo) {
				slots[car.getSlotNo()-1]=null;
				parkedCars.remove(car);
				parkigLotCurrCapacity--;
			}
		}
		
	}
}
