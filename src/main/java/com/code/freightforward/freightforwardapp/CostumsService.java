package com.code.freightforward.freightforwardapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // now we are slapping the service tag to let spring know this is the class doing the heavy lifting
public class CostumsService {
    @Autowired
    private ContainerRepository cRepo;
    @Autowired//we wired these two so that it creates new instances automatically temporarily
    private ParcelRepository pRepo;

    public String loadParcel(Long containerId, Parcel newParcel){
        Container container = cRepo.findById(containerId).get();

        //this is now the weight check
        int currentWeight = 0;
        //now we need to add each parcel's weight to current weight
        for(Parcel p : container.getParcels()){
            currentWeight += p.getWeight();
        }
        //now writing a defensive if statement
        if(currentWeight + newParcel.getWeight() > container.getMaxWeightCapacity()){
            return "Container is overweight -> REJECTED";
        }//to stop the heavy math from continuing if there is no point in continuing.
        newParcel.setContainer(container);
        //so now we declare to the parcel who the parent is
        container.getParcels().add(newParcel);//telling the parent to hold the child
        cRepo.save(container);//and saving it to the database
        return "Approved: Parcel loaded successfully";
    }
}
