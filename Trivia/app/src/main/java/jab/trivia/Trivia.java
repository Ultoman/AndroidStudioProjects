package jab.trivia;

/**
 * Created by JAB on 2/4/2018.
 */

/*
Structure for a trivia question when creating the game screen

 */

public class Trivia {
    //Create trivia attributes
    private int id;
    private String question, correct_ans, inc_ans_1, inc_ans_2, inc_ans_3;

    // Constructor
    public Trivia(int id, String question, String correct_ans, String inc_ans_1, String inc_ans_2, String inc_ans_3) {
        this.id = id;
        this.question = question;
        this.correct_ans = correct_ans;
        this.inc_ans_1 = inc_ans_1;
        this.inc_ans_2 = inc_ans_2;
        this.inc_ans_3 = inc_ans_3;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_ans() {
        return correct_ans;
    }

    public void setCorrect_ans(String correct_ans) {
        this.correct_ans = correct_ans;
    }

    public String getInc_ans_1() {
        return inc_ans_1;
    }

    public void setInc_ans_1(String inc_ans_1) {
        this.inc_ans_1 = inc_ans_1;
    }

    public String getInc_ans_2() {
        return inc_ans_2;
    }

    public void setInc_ans_2(String inc_ans_2) {
        this.inc_ans_2 = inc_ans_2;
    }

    public String getInc_ans_3() {
        return inc_ans_3;
    }

    public void setInc_ans_3(String inc_ans_3) {
        this.inc_ans_3 = inc_ans_3;
    }
}
