package com.parking.vehicleparking.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "ParkingLot")
@AllArgsConstructor
@Getter
@Setter
public class ParkingLot {
	@Id
	private int maxCapacity;
}
