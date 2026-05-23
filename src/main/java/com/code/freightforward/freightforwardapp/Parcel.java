package com.code.freightforward.freightforwardapp;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
//entity tag has been added to generate a table which has the same name as my class, enables us to pass the class into a jpaRepository
//and every variable becomes a column
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//so we tell the table to auto Increment sequentually
    private Long id;
    @ManyToOne//this is to declare that many records from one database are connected to One records of other
    @JoinColumn(name = "container_id")//joinColumn is to link the two databases together and specifies the name/configuration of a foreign key column.
    private Container container;//referencing the the parent
    @NotBlank(message = "Tracking number cannot be empty")
    private String trackingNumber;
    @Positive(message ="Weight must be a positive number")//this ensures that the weight is positive and if not outputs a java error with message
    private int weight;
    @Min(value = 1, message = "We do not ship for free")//this does the same thing, set's the minimum value, and if it's less than that sends a message java error
    private int profit;
    Parcel(){

    }
    //Setters and getters have now been set to handle info
    public Long getId() {
        return id;
    }

    public Container getContainer() {
        return container;
    }

    public int getProfit() {
        return profit;
    }

    public int getWeight() {
        return weight;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setContainer(Container container) {
        this.container = container;
    }
}
