package com.rental.rentalApp.repositories;

import com.rental.rentalApp.entities.Model;
import com.rental.rentalApp.entities.Brand;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ModelRepository extends CrudRepository<Model, Integer> {
  List<Model> findByModelName(String modelName);
  List<Model> findByBrand(Brand brand);
  List<Model> findAll();
}