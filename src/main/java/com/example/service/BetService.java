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
public class BetService {


    private final BetRepository betRepository;
    private final RouletteRepository rouletteRepository;
    public ProbabilityUtil probabilityUtil = new ProbabilityUtil();


    @Autowired
    public BetService(BetRepository betRepository, RouletteRepository rouletteRepository) {
        this.betRepository = betRepository;
        this.rouletteRepository = rouletteRepository;
    }

    public List<Bet> getBets() {
        List<Bet> bets = new ArrayList<>();
        betRepository.findAll().forEach(bets::add);
        return bets;
    }

    private Map<String, Object> validateBetConditions(Map<String, Object> payload, Map<String, Object> betResult){
        Map<String, Object> returnList = new HashMap<String, Object>();
        returnList.put("error" , false);
        returnList.put("success", true);
        returnList.put("message", "Congratulations");
        returnList.put("id", "");
        Optional<Roulette> roulette = rouletteRepository.findById((String) payload.get("rouletteId"));
        if(!roulette.isPresent()){
            returnList.put("error", true);
            returnList.put("message", "Roulette not exists");
            return returnList;
        }
        if(!roulette.get().isState()){
            returnList.put("error", true);
            returnList.put("message", "Roulette closed");
            return returnList;
        }
        if((Integer) payload.get("amount") > 10000){
            returnList.put("error", true);
            returnList.put("message", "Maximum bet amount exceeded");
            return returnList;
        }
        if((Integer) payload.get("number") < 0|| (Integer) payload.get("number") > 36 ){
            returnList.put("error", true);
            returnList.put("message", "Bet number out of range");
            return returnList;
        }
        if((Integer) payload.get("number") != (Integer) betResult.get("number")
            || (Boolean) payload.get("color") != (Boolean) betResult.get("color")){
            returnList.put("error", false);
            returnList.put("success", false);
            String betMessage = "Color: " + String.valueOf(betResult.get("color")) +
                    " Number: " + String.valueOf(betResult.get("number"));
            returnList.put("message", "FAILED: You haven't made the right bet" + betMessage);
            return returnList;
        }
        return returnList;
    }

    public Map<String, Object> createBet(Map<String, Object> payload) {
        Bet bet = new Bet();
        Map<String, Object> betResult =  probabilityUtil.betRoulette();
        Map<String, Object> validate = this.validateBetConditions(payload, betResult);
        if (!(boolean)validate.get("error")){
            bet.setRoulette((String) payload.get("rouletteId"));
            bet.setNumber((Integer) payload.get("number"));
            bet.setColor((Boolean) payload.get("color"));
            bet.setAmount((Integer) payload.get("amount"));
            bet.setResultNumber((int)betResult.get("number"));
            bet.setResultColor((boolean)betResult.get("color"));
            bet.setResult((boolean)validate.get("success"));
            bet = betRepository.save(bet);
            validate.put("id", bet.getId());
        }
        return validate;
    }
}
