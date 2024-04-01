package com.animaladoption.animaladoption.dao;

import java.util.List;

import com.animaladoption.animaladoption.model.Animal;

public interface AnimalDao {

  public List<Animal> getAllAnimals();

  public Animal getAnimalById(int id);

  public void addAnimal(Animal animal);

  public void updateAnimal(Animal animal);

  public void deleteAnimalById(int id);
}
