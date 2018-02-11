package jab.trivia;

/**
 * Created by JAB on 2/4/2018.
 */

public class TriviaAPI {

    //Create variables to hold the parameters of Trivia API
    private int amount;
    private int category;
    private String difficulty;
    private String type;

    // Constructor
    public TriviaAPI()
    {
        amount = 10;
        category = 0;
        difficulty = "easy";
        type = "multiple";
    }

    //Alt Constructor for API

    public TriviaAPI(int amount, int category, String difficulty, String type) {
        this.amount = amount;
        this.category = category;
        this.difficulty = difficulty;
        this.type = type;
    }

    //Generates API url
    public String generateURL()
    {
        String url = "https://opentdb.com/api.php?";
        url += "amount=" + String.valueOf(amount) + "&";
        if (category != 0)
        {
            url += "category=" + String.valueOf(category) + "&";
        }
        url += "difficulty=" + difficulty + "&";
        url += "type=" + type;

        return url;
    }

    public String generateCategoryURL()
    {
        return "https://opentdb.com/api_category.php";
    }



    //Getters and Setters for TriviaAPI

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
