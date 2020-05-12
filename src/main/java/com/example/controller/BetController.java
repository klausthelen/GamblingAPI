package com.example.controller;

import com.example.model.Bet;
import com.example.model.Response;
import com.example.model.Roulette;
import com.example.service.BetService;
import com.example.service.RouletteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/bet")
public class BetController {

    private final BetService betService;
    private Response response = Response.getResponse("",false,"");

    @Autowired
    public BetController(BetService betService) {
        this.betService = betService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Bet> getBets() {
        return betService.getBets();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response createBet(@RequestBody Map<String, Object> payload ) {
        Map<String, Object>  result = betService.createBet(payload);
        return response.setResponse(result);
    }
}
