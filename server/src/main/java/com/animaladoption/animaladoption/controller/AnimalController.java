package com.animaladoption.animaladoption.controller;

import com.animaladoption.animaladoption.dao.AnimalDao;
import com.animaladoption.animaladoption.model.Animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

  private final AnimalDao animalDao;

  @Autowired
  public AnimalController(AnimalDao animalDao) {
    this.animalDao = animalDao;
  }

  @GetMapping
  public List<Animal> getAllAnimals() {
    return animalDao.getAllAnimals();
  }

  @GetMapping("/{id}")
  public Animal getAnimalById(@PathVariable int id) {
    return animalDao.getAnimalById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void addAnimal(@RequestBody Animal animal) {
    animalDao.addAnimal(animal);
  }

  @PutMapping
  public void updateAnimal(@RequestBody Animal animal) {
    animalDao.updateAnimal(animal);
  }

  @DeleteMapping("/{id}")
  public void deleteAnimalById(@PathVariable int id) {
    animalDao.deleteAnimalById(id);
  }
}
