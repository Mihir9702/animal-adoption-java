package com.animaladoption.animaladoption.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.animaladoption.animaladoption.exception.DaoException;
import com.animaladoption.animaladoption.model.Animal;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAnimalDao implements AnimalDao {

  private JdbcTemplate jdbcTemplate;

  public JdbcAnimalDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Animal> getAllAnimals() {
    String sql = "SELECT * FROM animals";
    List<Animal> animals = new ArrayList<>();

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

      while (results.next()) {
        Animal animal = mapRowToAnimal(results);
        animals.add(animal);
      }

    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Error connecting to database", e);
    } catch (DataAccessException e) {
      throw new DaoException("Error retrieving animals from database", e);
    }

    return animals;
  }

  @Override
  public Animal getAnimalById(int id) {
    String sql = "SELECT * FROM animals WHERE id = ?";
    Animal animal = null;

    try {
      SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

      if (results.next()) {
        animal = mapRowToAnimal(results);
      }

    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Error connecting to database", e);
    } catch (DataAccessException e) {
      throw new DaoException("Error retrieving animal from database", e);
    }

    return animal;
  }

  @Override
  public void addAnimal(Animal animal) {
    String sql = "INSERT INTO animals (name, species, breed, age) VALUES (?, ?, ?, ?)";

    try {
      jdbcTemplate.update(sql, animal.getName(), animal.getSpecies(), animal.getBreed(), animal.getAge());
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Error connecting to database", e);
    } catch (DataAccessException e) {
      throw new DaoException("Error adding animal to database", e);
    }
  }

  @Override
  public void updateAnimal(Animal animal) {
    String sql = "UPDATE animals SET name = ?, species = ?, breed = ?, age = ? WHERE id = ?";

    try {
      jdbcTemplate.update(sql, animal.getName(), animal.getSpecies(), animal.getBreed(), animal.getAge(),
          animal.getId());
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Error connecting to database", e);
    } catch (DataAccessException e) {
      throw new DaoException("Error updating animal in database", e);
    }
  }

  @Override
  public void deleteAnimalById(int id) {
    String sql = "DELETE FROM animals WHERE id = ?";

    try {
      jdbcTemplate.update(sql, id);
    } catch (CannotGetJdbcConnectionException e) {
      throw new DaoException("Error connecting to database", e);
    } catch (DataAccessException e) {
      throw new DaoException("Error deleting animal from database", e);
    }
  }

  private Animal mapRowToAnimal(SqlRowSet row) {
    Animal animal = new Animal();

    animal.setId(row.getInt("id"));
    animal.setName(row.getString("name"));
    animal.setSpecies(row.getString("species"));
    animal.setBreed(row.getString("breed"));
    animal.setAge(row.getDouble("age"));

    return animal;
  }
}
