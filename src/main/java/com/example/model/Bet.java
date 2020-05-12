package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@RedisHash("Bet")
public class Bet  implements Serializable {
    @Id
    private UUID id;

    private String roulette;

    private Integer number;

    private boolean color;

    private Integer resultNumber;

    private boolean resultColor;

    private Integer amount;

    private boolean result;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public Integer getResultNumber() {
        return resultNumber;
    }

    public void setResultNumber(Integer resultNumber) {
        this.resultNumber = resultNumber;
    }

    public boolean isResultColor() {
        return resultColor;
    }

    public void setResultColor(boolean resultColor) {
        this.resultColor = resultColor;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRoulette() {
        return roulette;
    }

    public void setRoulette(String roulette) {
        this.roulette = roulette;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
