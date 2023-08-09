package com.bnta.chocolate.models;

import java.util.ArrayList;
import java.util.List;

public class ChocolateDTO {
    private String name;
    private int cocoaPercentage;

    private List<Long> estateIds;

    public ChocolateDTO(String name, int cocoaPercentage ,List<Long> estateIds){
        this.name = name;
        this.cocoaPercentage = cocoaPercentage;
        this.estateIds = estateIds;
    }

    public ChocolateDTO(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCocoaPercentage() {
        return cocoaPercentage;
    }

    public void setCocoaPercentage(int cocoaPercentage) {
        this.cocoaPercentage = cocoaPercentage;
    }

    public List<Long> getEstateIds() {
        return estateIds;
    }

    public void setEstateIds(List<Long> estateIds) {
        this.estateIds = estateIds;
    }
}
