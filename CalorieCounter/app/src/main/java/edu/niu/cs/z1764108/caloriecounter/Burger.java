package edu.niu.cs.z1764108.caloriecounter;

/**
 * Created by JAB on 1/31/2017.
 */

public class Burger {
    //constants
    static final int BEEF = 90,
                     TURKEY = 170,
                     VEGGIE = 150,
                     CHEDDAR = 113,
                     MOZZ = 78,
                     BACON = 86;

    private int pattyCalories, cheeseCalories, baconCalories, sauceCalories;

    public Burger() {
        pattyCalories = BEEF;
        cheeseCalories = 0;
        baconCalories = 0;
        sauceCalories = 0;
    }

    public void setPattyCalories(int pattyCalories) {
        this.pattyCalories = pattyCalories;
    }

    public void setCheeseCalories(int cheeseCalories) {
        this.cheeseCalories = cheeseCalories;
    }

    public void setBaconCalories(boolean hasBacon) {
        if(hasBacon)
            this.baconCalories = BACON;
        else
            this.baconCalories = 0;
    }

    public void setSauceCalories(int sauceCalories) {
        this.sauceCalories = sauceCalories;
    }

    public int getTotalCalories()
    {
        return pattyCalories + cheeseCalories + baconCalories + sauceCalories;
    }
}
