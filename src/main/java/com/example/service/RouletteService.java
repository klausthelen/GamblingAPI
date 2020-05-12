package com.example.service;

import com.example.model.Bet;
import com.example.model.Roulette;
import com.example.repository.BetRepository;
import com.example.repository.RouletteRepository;
import com.example.utils.ProbabilityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RouletteService {


    private final BetRepository betRepository;
    private final RouletteRepository rouletteRepository;
    public ProbabilityUtil probabilityUtil = new ProbabilityUtil();

    @Autowired
    public RouletteService(BetRepository betRepository,RouletteRepository rouletteRepository) {
        this.betRepository = betRepository;
        this.rouletteRepository = rouletteRepository;
    }

    public List<Roulette> getRouletes() {
        List<Roulette> roulettes = new ArrayList<>();
        rouletteRepository.findAll().forEach(roulettes::add);
        return roulettes;
    }

    public List<Bet> closeRoulete(String rouletteId) {
        Optional<Roulette> roulette = rouletteRepository.findById(rouletteId);
        roulette.get().setState(false);
        rouletteRepository.save(roulette.get());
        List<Bet> bets = new ArrayList<>();
        betRepository.findByroulette(rouletteId).forEach(bets::add);
        return bets;
    }

    public Map<String, Object> addRoulette() {
        Map<String, Object> returnList = new HashMap<String, Object>();
        Roulette roulette = new Roulette();
        roulette.setState(false);
        roulette = rouletteRepository.save(roulette);
        returnList.put("error" , false);
        returnList.put("message", "Roulette created");
        returnList.put("id", roulette.getId());
        return returnList;
    }

    public Map<String, Object> openRoulette(String rouletteId) {
        Map<String, Object> returnList = new HashMap<String, Object>();
        returnList.put("id", rouletteId);
        Optional<Roulette> roulette = rouletteRepository.findById(rouletteId);
        if(!roulette.isPresent()){
            returnList.put("error", true);
            returnList.put("message", "Roulette not exists");
            return returnList;
        }
        boolean successfulness = probabilityUtil.successfulness();
        if(successfulness){
            roulette.get().setState(true);
        }
        rouletteRepository.save(roulette.get());
        returnList.put("error" , !successfulness);
        String message = successfulness ? "successful" : "denied";
        returnList.put("message", message);
        return returnList;
    }
}
