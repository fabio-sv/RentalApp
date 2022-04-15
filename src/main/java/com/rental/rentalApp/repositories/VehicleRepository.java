package com.rental.rentalApp.repositories;

import com.rental.rentalApp.entities.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
    List<Vehicle> findByVIN(String VIN);

    List<Vehicle> findByAvailable(Boolean Available);

    long deleteByVin(String VIN);
}
