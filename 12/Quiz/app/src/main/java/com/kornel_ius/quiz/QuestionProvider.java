package com.kornel_ius.quiz;

import java.util.Random;

/**
 * Created by Kornel_ius.
 */

public class QuestionProvider {

    private Question mQuestion1;
    private Question mQuestion2;
    private Question mQuestion3;

    public QuestionProvider() {
        initQuestions();
    }

    private void initQuestions() {
        mQuestion1 = new Question("Do you like placki?", new Answer("Tak1", false), new Answer("Może", true), new Answer("Nie", false), new Answer("JEszcze jak", false));
        mQuestion2 = new Question("Do you like placki2?", new Answer("Tak2", false), new Answer("Może", true), new Answer("Nie", false), new Answer("JEszcze jak", false));
        mQuestion3 = new Question("Do you like placki3?", new Answer("Tak3", false), new Answer("Może", true), new Answer("Nie", false), new Answer("JEszcze jak", false));

    }

    public Question getRandomQuestion() {
        Random r = new Random();
        int rInt = r.nextInt(3);
        if (rInt == 0) {
            return mQuestion1;
        } else if (rInt == 1) {
            return mQuestion2;
        } else {
            return mQuestion3;
        }
    }
}
