package com.code.freightforward.freightforwardapp;

public class ParcelDTO {
    private String trackingNumber;
    private int Weight;
    private int profit;

    ParcelDTO(){

    }

    public int getWeight() {
        return Weight;
    }

    public int getProfit() {
        return profit;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }
}
