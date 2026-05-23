package com.code.freightforward.freightforwardapp;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity//this is now a seperate table with name of container
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //so this ID is now unique to the table container, therefore if we are accessing anything from container table
    //we will need the id for the container to access that specific info
    private String containerCode;
    private int maxWeightCapacity;
    @OneToMany(mappedBy = "container", cascade = CascadeType.ALL)//the mapped by is important since it doesn't create a third table
    //instead if a container is destroyed, so are the parcel's inside of it
    private List<Parcel> parcels = new ArrayList<>();//we just declared a list is based on the variables existed in the Parcel class
    //okay variables have now been declared we need getters and setters to set values
    Container(){

    }

    public Long getId() {
        return id;
    }

    public List<Parcel> getParcels() {
        return parcels;
    }

    public int getMaxWeightCapacity() {
        return maxWeightCapacity;
    }

    public String getContainerCode() {
        return containerCode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContainerCode(String containerCode) {
        this.containerCode = containerCode;
    }

    public void setMaxWeightCapacity(int maxWeightCapacity) {
        this.maxWeightCapacity = maxWeightCapacity;
    }

    public void setParcels(List<Parcel> parcels) {
        this.parcels = parcels;
    }
    //getters and setters have now been set. variables are being handled properly
}
