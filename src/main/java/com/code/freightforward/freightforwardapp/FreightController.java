package com.code.freightforward.freightforwardapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FreightController {

    // Hiring the workers we built
    @Autowired
    private CostumsService costumsService;

    @Autowired
    private OptimizerService optimizerService;

    @Autowired
    private ContainerRepository containerRepository;

    // Endpoint 1: Build an empty shipping container
    @PostMapping("/create-container")
    public String createContainer(@RequestBody ContainerDTO dto) {
        Container container = new Container();
        container.setContainerCode(dto.getContainerCode());
        container.setMaxWeightCapacity(dto.getMaxWeightCapacity());

        containerRepository.save(container);
        return "Container created successfully!";
    }

    // Endpoint 2: The Customs Gate (Load a parcel into a specific container)
    @PostMapping("/load/{containerId}")
    public String loadParcel(@PathVariable Long containerId, @RequestBody ParcelDTO dto) {
        Parcel newParcel = new Parcel();
        newParcel.setTrackingNumber(dto.getTrackingNumber());
        newParcel.setWeight(dto.getWeight());
        newParcel.setProfit(dto.getProfit());

        return costumsService.loadParcel(containerId, newParcel);
    }

    // Endpoint 3: The Brain (Trigger the Knapsack DP algorithm)
    @PostMapping("/optimize/{containerId}")
    public String optimize(@PathVariable Long containerId) {
        return optimizerService.optimizeContainer(containerId);
    }
}