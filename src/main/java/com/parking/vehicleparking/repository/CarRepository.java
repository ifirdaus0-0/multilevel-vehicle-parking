package com.parking.vehicleparking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.parking.vehicleparking.domain.Car;

public interface CarRepository extends MongoRepository<Car, Integer> {

}
