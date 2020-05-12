package com.example.repository;

import com.example.model.Roulette;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouletteRepository extends CrudRepository<Roulette, String> {
    List<Roulette> findAll();
}
