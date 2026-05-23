package com.code.freightforward.freightforwardapp;

public class ContainerDTO {
    private String containerCode;
    private int maxWeightCapacity;
    ContainerDTO(){

    }

    public int getMaxWeightCapacity() {
        return maxWeightCapacity;
    }

    public String getContainerCode() {
        return containerCode;
    }

    public void setContainerCode(String containerCode) {

        this.containerCode = containerCode;
    }

    public void setMaxWeightCapacity(int maxWeightCapacity) {
        this.maxWeightCapacity = maxWeightCapacity;
    }
}
