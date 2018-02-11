package edu.niu.cs.z1764108.cartooncharacter;

/**
 * Created by JAB on 2/14/2017.
 */

public class Character {
    private String characterDescription;
    private int characterID;

    public Character(String characterDescription, int characterID) {
        this.characterDescription = characterDescription;
        this.characterID = characterID;
    }

    public String getCharacterDescription() {
        return characterDescription;
    }

    public void setCharacterDescription(String characterDescription) {
        this.characterDescription = characterDescription;
    }

    public int getCharacterID() {
        return characterID;
    }

    public void setCharacterID(int characterID) {
        this.characterID = characterID;
    }
}
