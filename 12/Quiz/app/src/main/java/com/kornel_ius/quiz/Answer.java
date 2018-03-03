package com.kornel_ius.quiz;

/**
 * Created by Kornel_ius.
 */

public class Answer {

    private String mText;
    private boolean mCorrect;

    public Answer(String text, boolean correct) {
        mText = text;
        mCorrect = correct;
    }

    public String getText() {
        return mText;
    }

    public boolean getCorrect() {
        return mCorrect;
    }
}
