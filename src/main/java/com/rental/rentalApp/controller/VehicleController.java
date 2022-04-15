package com.rental.rentalApp.controller;

import java.util.*;

import com.rental.rentalApp.entities.Vehicle;
import com.rental.rentalApp.repositories.VehicleRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rental-app/api/vehicle")
public class VehicleController {

  private final VehicleRepository vehicles;

  //constructor
	private VehicleController(VehicleRepository vehicle) {
		this.vehicles = vehicle;
	}

  //update entities by the VIN{id}
  @PatchMapping("{id}")
	public ResponseEntity<String> patchVehicle(@PathVariable Integer id, @RequestBody Vehicle newData) {
		StringBuilder response = new StringBuilder();

		Vehicle vehicle = vehicles.findById(id).get();

		if (vehicle == null) {
			return ResponseEntity.badRequest().build();
		}

		if (newData.getLastService() != null) {
			vehicle.setLastService(newData.getLastService());
			response.append(String.format("Vehicle last serviced on: "+newData.getLastService() +"\n"));
		}

		if (newData.getDepositAmount() != null) {
			vehicle.setDepositAmount(newData.getDepositAmount());
			response.append(String.format("Deposit for vehicle updated: "+newData.getDepositAmount() +"\n"));
		}

		if (newData.getHourlyRate() != null) {
			vehicle.setHourlyRate(newData.getHourlyRate());
			response.append(String.format("Hourly rate for vehicle updated: "+newData.getHourlyRate() +"\n"));
		}

		if (newData.getDailyRate() != null) {
			vehicle.setDailyRate(newData.getDailyRate());
			response.append(String.format("Daily rate for vehicle updated: "+newData.getDailyRate() +"\n"));
		}

    if (newData.getMileage() != null) {
			vehicle.setMileage(newData.getMileage());
			response.append(String.format("New milage for vehicle updated: "+newData.getMileage() +"\n"));
		}

		return ResponseEntity.ok(response.toString());
	}

  //remove vehicle from bd
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteVehicle(@PathVariable Integer id, @RequestParam Optional<String> token) {
		final String securityKey = "g2G7aoTyqQeDG4liY5ZmBAQr8V7M4v3BKSeUNd5u";

		if (token.isPresent() && token.get().equals(securityKey)) {
			Vehicle vehicle = vehicles.findById(id).get();
			vehicles.deleteById(id);

			return ResponseEntity.ok(String.format("Deleted "+vehicle.getVIN()));
		}

		return ResponseEntity.badRequest().build();
	}

}