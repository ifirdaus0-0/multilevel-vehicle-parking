package com.parking.vehicleparking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.vehicleparking.domain.ParkingLot;
import com.parking.vehicleparking.reposatory.ParkingLotRepo;

@Service
public class ParkingLotService {

	@Autowired
	ParkingLotRepo parkLotRepo;
	
	public String createParkingLot(int numberOfSlots) {
		boolean result = ParkingLot.getInstance().createParkingLot(numberOfSlots);
		if(result) return String.format("Created a parking lot with %d slots", numberOfSlots);
		else return "error";
	}
	
}