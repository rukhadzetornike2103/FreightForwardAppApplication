package com.code.freightforward.freightforwardapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service//another service tag, telling spring boot that this is another heavy class
public class OptimizerService {
    @Autowired//time to hire the params from the other class
    private ContainerRepository cRepo;
    @Autowired//same here
    private ParcelRepository pRepo;

    public String optimizeContainer(Long containerId){
        //we passed the long to give java/spring a guideline on where to search for parcels and info
        Container container = cRepo.findById(containerId).get();
        int currentWeight = 0;
        for(Parcel c : container.getParcels()){
            currentWeight += c.getWeight();
        }
        int remainingCapacity = container.getMaxWeightCapacity() - currentWeight;//subtracting current weight from maxWeight
        if(pRepo.findByContainerIsNull().isEmpty()){
            return "There isn't anything to do";
        }
        List<Parcel> warehouse = pRepo.findByContainerIsNull();
        int numOfParcels = warehouse.size();
        int[][] dp = new int[numOfParcels + 1][remainingCapacity + 1];

        for(int r = 1; r <= numOfParcels; r++){
            //okay we just declared a new instance of parcel so we can get the attributes from it
            Parcel currentParcel = warehouse.get(r - 1);//-1 for index matching
            int pWeight = currentParcel.getWeight();//now we declare pWeight and grab it from the parcel insstance
            int pProfit = currentParcel.getProfit();//same here
            for(int c = 1; c <= remainingCapacity; c++){
                if(pWeight < c){
                    int keepIt = pProfit + dp[r-1][c - pWeight];
                    int leaveIt = dp[r-1][c];
                    dp[r][c] = Math.max(keepIt, leaveIt);
                } else {
                    dp[r][c] = dp[r-1][c];
                }
            }
        }
        int maxProfit = dp[numOfParcels][remainingCapacity];
        int w = remainingCapacity;
        List<Parcel> packedParcels = new ArrayList<>();
        for(int i = numOfParcels; i> 0 && maxProfit > 0; i--){
            if(maxProfit != dp[i-1][w]){
                Parcel p = warehouse.get(i-1);
                packedParcels.add(p);
                maxProfit -= p.getProfit();
                w-=p.getWeight();
            }
        }

        for(Parcel p : packedParcels){
            p.setContainer(container);
            container.getParcels().add(p);
        }
        cRepo.save(container);

        return "Optimization complete. Packed " + packedParcels.size() + " Parcels";


    }
}
