package com.kornel_ius.quiz;

/**
 * Created by Kornel_ius.
 */

public class Question {

    private String mQuestion;
    private Answer mAnswer1;
    private Answer mAnswer2;
    private Answer mAnswer3;
    private Answer mAnswer4;

    public Question(String question, Answer answer1, Answer answer2, Answer answer3, Answer answer4) {
        mQuestion = question;
        mAnswer1 = answer1;
        mAnswer2 = answer2;
        mAnswer3 = answer3;
        mAnswer4 = answer4;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public Answer getAnswer1() {
        return mAnswer1;
    }

    public Answer getAnswer2() {
        return mAnswer2;
    }

    public Answer getAnswer3() {
        return mAnswer3;
    }

    public Answer getAnswer4() {
        return mAnswer4;
    }


}
