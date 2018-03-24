package com.example.z1764108.puzzlegame;

import java.util.Random;

/**
 * Created by Z1764108 on 1/29/2018.
 */

public class Puzzle {
    private String[] parts;
    private Random random = new Random();
    public static final int NUM_PARTS = 6;
    public static final int NUM_WORDS = 5;
    public int word_num;

    public Puzzle()
    {
        int word_num = random.nextInt(NUM_WORDS + 1);
        switch (word_num){
            case 0:
                parts = new String[NUM_PARTS];
                parts[0] = "C";
                parts[1] = "A";
                parts[2] = "S";
                parts[3] = "T";
                parts[4] = "L";
                parts[5] = "E";
                break;
            case 1:
                parts = new String[NUM_PARTS];
                parts[0] = "B";
                parts[1] = "E";
                parts[2] = "Y";
                parts[3] = "O";
                parts[4] = "N";
                parts[5] = "D";
                break;
            case 2:
                parts = new String[NUM_PARTS];
                parts[0] = "D";
                parts[1] = "A";
                parts[2] = "N";
                parts[3] = "G";
                parts[4] = "E";
                parts[5] = "R";
                break;
            case 3:
                parts = new String[NUM_PARTS];
                parts[0] = "M";
                parts[1] = "A";
                parts[2] = "S";
                parts[3] = "T";
                parts[4] = "E";
                parts[5] = "R";
                break;
            case 4:
                parts = new String[NUM_PARTS];
                parts[0] = "S";
                parts[1] = "I";
                parts[2] = "G";
                parts[3] = "N";
                parts[4] = "A";
                parts[5] = "L";
                break;
            case 5:
                parts = new String[NUM_PARTS];
                parts[0] = "W";
                parts[1] = "E";
                parts[2] = "I";
                parts[3] = "G";
                parts[4] = "H";
                parts[5] = "T";
                break;
        }



    }
    public boolean solved(String [] solution)
    {
        if (solution != null && solution.length == parts.length)
        {
            for (int i = 0; i < parts.length; i++)
            {
                if (!solution[i].equals(parts[i]))
                {
                    return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }
    public String[] scramble()
    {
        String [] scrambled = new String[parts.length];
        for (int i = 0; i < scrambled.length; i++)
        {
            scrambled[i] = parts[i];
        }
        while (solved(scrambled))
        {
            for (int i = 0; i < scrambled.length; i++)
            {
                int n = random.nextInt(scrambled.length - i) + i;
                String temp = scrambled[i];
                scrambled[i] = scrambled[n];
                scrambled[n] = temp;
            }//for
        }//while
        return scrambled;
    }

    public String [] getString()
    {
        return parts;
    }

    public int getNumParts()
    {
        return parts.length;
    }
}
