package com.example.cashincashoutapp;

public class Cashflow {
    private String id;
    private String nominal;
    private String date;
    private String description;
    private String type;

    public Cashflow(String id, String nominal, String date, String desc, String type){
        this.id = id;
        this.nominal = nominal;
        this.date = date;
        this.description = desc;
        this.type = type;
    }

    public String GetID(){
        return this.id;
    }

    public String GetNominal(){
        return this.nominal;
    }

    public String GetDate(){
        return this.date;
    }

    public String GetDesc(){
        return this.description;
    }

    public String GetType(){
        return this.type;
    }
}
