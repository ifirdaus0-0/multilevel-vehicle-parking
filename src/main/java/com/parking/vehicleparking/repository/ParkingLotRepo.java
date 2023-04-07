package com.parking.vehicleparking.repository;

import org.springframework.stereotype.Repository;

import com.parking.vehicleparking.domain.Car;
import com.parking.vehicleparking.domain.ParkingLot;

import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface ParkingLotRepo extends MongoRepository<ParkingLot, Integer>{

}
