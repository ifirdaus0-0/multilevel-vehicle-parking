package com.parking.vehicleparking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.vehicleparking.service.ParkingLotService;


@RestController
public class ParkingLotController {
	
	@Autowired
	ParkingLotService parkingLotService;
	
	@GetMapping("/create_parking_lot")
	public String creatParkingLot(@RequestParam int number) {
		return parkingLotService.createParkingLot(number);
	}
	
//	@GetMapping("/park")
//	public String allocateSlot(@RequestParam String carnumber,String color) {
//		return parkingLotService.slotAllocate(carnumber, color);
//	}

}
