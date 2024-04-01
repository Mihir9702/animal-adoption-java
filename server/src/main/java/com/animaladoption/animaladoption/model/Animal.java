package com.animaladoption.animaladoption.model;

public class Animal {

  private int id;
  private String name;
  private String species;
  private String breed;
  private double age;

  public Animal(int id, String name, String species, String breed, double age) {
    this.id = id;
    this.name = name;
    this.species = species;
    this.breed = breed;
    this.age = age;
  }

  public Animal() {
  }

  // Getters and setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSpecies() {
    return species;
  }

  public void setSpecies(String species) {
    this.species = species;
  }

  public String getBreed() {
    return breed;
  }

  public void setBreed(String breed) {
    this.breed = breed;
  }

  public double getAge() {
    return age;
  }

  public void setAge(double age) {
    this.age = age;
  }
}
