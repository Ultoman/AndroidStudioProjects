package edu.niu.cs.z1764108.candystore;

import android.content.Context;
import android.widget.Button;

/**
 * Created by JAB on 4/25/2017.
 */

public class CandyButton extends Button{
    private Candy candy;

    public CandyButton(Context context, Candy newcandy) {
        super(context);
        this.candy = newcandy;
    }

    public double getPrice()
    {
        return candy.getPrice();
    }

}
