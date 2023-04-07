package com.parking.vehicleparking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.vehicleparking.exception.ParkingLotException;
import com.parking.vehicleparking.service.ParkingLotService;


@RestController
public class ParkingLotController {
	
	@Autowired
	ParkingLotService parkingLotService;
	
	
	@GetMapping("/create_parking_lot")
	public String creatParkingLot(@RequestParam int number) {
		return parkingLotService.createParkingLot(number);
	}
	
	@GetMapping("/park")
	public String parkCar(@RequestParam String carnumber,String color) {
		return parkingLotService.parkCar(carnumber, color);
	}
	
	@GetMapping("/registration_numbers_for_cars_with_colour")
	public List<String> getRegNoByColor(@RequestParam String color){
		return parkingLotService.getRegNoByColor(color);
	}

	
	@GetMapping("/slot_number_for_registration_number")
	public List<Integer> getSlotNoByRegNo(@RequestParam String regNo){
		return parkingLotService.getSlotNoByRegNo(regNo);
	}
	
	
	@GetMapping("/slot_numbers_for_cars_with_colour")
	public List<Integer> getSlotNoByColor(@RequestParam String color){
		List<Integer> slots =parkingLotService.getSlotByColor(color);
		if(slots.isEmpty()) throw new ParkingLotException("Not Found");
		return slots;
	}
	
	@GetMapping("/leave")
	public String vacate(@RequestParam int slotNo) {
		return parkingLotService.vacateParkingSlot(slotNo);
	}
	
	@GetMapping("/status")
	public String parkingLotStatus() {
		return parkingLotService.parkingLotStatus();
	}
	
}
