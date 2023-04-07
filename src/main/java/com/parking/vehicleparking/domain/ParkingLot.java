package com.parking.vehicleparking.domain;
import java.util.HashMap;

import com.parking.vehicleparking.domain.Car;

public class ParkingLot {
	
	private static ParkingLot parkingLot = null;
	private int maxCapacity;
	private Car[] slots;
	private int currentCapacity;
	private HashMap<Car, Boolean> parkedCars;
	
	private ParkingLot() {}
	
	public static ParkingLot getInstance() {
		if(parkingLot==null) {
			parkingLot = new ParkingLot();
		}
		return parkingLot;
	}
	

	
	public boolean createParkingLot(int maxCapacity) {
		if(parkingLot!=null && parkingLot.maxCapacity==0) {
		 this.maxCapacity=maxCapacity;
		 this.slots = new Car[maxCapacity];
		 this.parkedCars=new HashMap<>();
		 return true;
		}else {
			throw new IllegalArgumentException("Parking lot is already initialised");
		}
	}
	
	public boolean isFull() {
		return currentCapacity==maxCapacity;
	}
	
	public int parkCar(Car car) {
		if(isFull()) return -1;
		for(int i=0; i<maxCapacity; i++) {
			if(parkedCars.containsKey(car)) return 0;
			else {
			if(slots[i]==null) {
				slots[i]=car;
				parkedCars.put(car, true);
				currentCapacity++;
				return i+1;
			}
			
		   }
		}
		return -1;
	}
	
	

}
