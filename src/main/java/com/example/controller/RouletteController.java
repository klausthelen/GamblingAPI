package com.example.controller;

import com.example.model.Bet;
import com.example.model.Response;
import com.example.model.Roulette;
import com.example.service.RouletteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/roulette")
public class RouletteController {

    private final RouletteService rouletteService;
    private Response response = Response.getResponse("",false,"");

    @Autowired
    public RouletteController(RouletteService rouletteService) {
        this.rouletteService = rouletteService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Roulette> getRouletes() {
        return rouletteService.getRouletes();
    }

    @RequestMapping(value = "/close", method = RequestMethod.POST)
    public List<Bet> closeRoulette(@RequestBody Map<String, Object> payload) {
        return rouletteService.closeRoulete((String) payload.get("rouletteId"));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response addRoulette() {
        Map<String, Object>  result = rouletteService.addRoulette();
        return response.setResponse(result);
    }

    @RequestMapping(value = "/open", method = RequestMethod.POST)
    public Response openRoulette(@RequestBody Map<String, Object> payload ) {
        Map<String, Object>  result = rouletteService.openRoulette((String) payload.get("id"));
        return response.setResponse(result);
    }
}
