package com.devlab.tamboon.data;

public class Donation {

    private String name;
    private String token;
    private Integer amount;


    public Donation(String name,String token, Integer amount){
        this.name = name;
        this.token = token;
        this.amount = amount;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
