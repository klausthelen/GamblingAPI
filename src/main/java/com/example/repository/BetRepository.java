package com.example.repository;

import com.example.model.Bet;
import com.example.model.Roulette;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetRepository extends CrudRepository<Bet, String> {
    List<Bet> findAll();

    List<Bet> findByroulette(String rouletteId);
}
