package com.example.utils;

import java.util.HashMap;
import java.util.Map;

public class ProbabilityUtil {

    public ProbabilityUtil (){

    }

    public boolean successfulness(){
        double random = Math.random();
        if (random < 0.9){
            return true;
        }
        else {
            return false;
        }
    }

    public Map<String, Object> betRoulette(){
        Map<String, Object> returnList = new HashMap<String, Object>();
        int randomColor =  (int) Math.floor(Math.random() * 2);
        int randomNumber = (int) Math.floor(Math.random() * 37);
        boolean boolColor = (randomColor == 1);
        returnList.put("color" , boolColor);
        returnList.put("number", randomNumber);
        return  returnList;
    }
}
