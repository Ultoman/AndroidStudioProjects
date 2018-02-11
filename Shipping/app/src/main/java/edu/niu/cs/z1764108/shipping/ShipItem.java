package edu.niu.cs.z1764108.shipping;

/**
 * Created by JAB on 1/26/2017.
 */

public class ShipItem {

    //Instance variables
    private Integer weight;
    private Double baseCost, addedCost, totalCost;

    //Constants
    static final Double BASE_AMOUNT = 3.00;
    static final Double ADDED_AMOUNT = 0.50;
    static final Integer BASE_WEIGHT = 16;
    static final Double EXTRA_OUNCES = 4.0;

    public ShipItem() {
        weight = 0;
        baseCost = BASE_AMOUNT;
        addedCost = 0.0;
        totalCost = 0.0;
    }

    public void setWeight(Integer w) {
        this.weight = w;
        computeCost();
    }

    public void computeCost() {
        addedCost = 0.0;
        baseCost = BASE_AMOUNT;

        if(weight <= 0)
            baseCost = 0.00;
        else if (weight > BASE_WEIGHT)
            addedCost = Math.ceil( (weight - BASE_WEIGHT) / EXTRA_OUNCES) * ADDED_AMOUNT;

        totalCost = baseCost + addedCost;
    }

    public Double getBaseCost() {
        return baseCost;
    }

    public Double getAddedCost() {
        return addedCost;
    }

    public Double getTotalCost() {
        return totalCost;
    }
}//ShipItem
