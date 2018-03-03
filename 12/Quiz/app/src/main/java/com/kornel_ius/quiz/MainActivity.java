package com.kornel_ius.quiz;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private QuestionProvider mQuestionProvider;

    private TextView mQuestionTextView;
    private TextView mAnswer1TextView;
    private TextView mAnswer2TextView;
    private TextView mAnswer3TextView;
    private TextView mResultInfoTextView;
    private TextView mAnswer4TextView;
    private Question mCurrentQuestion;
    private Answer mSelectedAnswer;
    private Button mNextQuestionButton;
    private boolean mClickable;
    private Drawable mNeutralBackground;
    private Drawable mSelectedBackground;
    private Drawable mWrongBackground;
    private Drawable mRightBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initNewQuestion();
    }

    private void init() {
        mQuestionProvider = new QuestionProvider();
        mQuestionTextView = (TextView) findViewById(R.id.questionTextView);
        mAnswer1TextView = (TextView) findViewById(R.id.answer1TextView);
        mAnswer2TextView = (TextView) findViewById(R.id.answer2TextView);
        mAnswer3TextView = (TextView) findViewById(R.id.answer3TextView);
        mAnswer4TextView = (TextView) findViewById(R.id.answer4TextView);
        mResultInfoTextView = (TextView) findViewById(R.id.resultInfoTextView);
        mNextQuestionButton = (Button) findViewById(R.id.nextQuestionButton);

        mNeutralBackground = getResources().getDrawable(R.drawable.rounded_corners_neutral);
        mSelectedBackground = getResources().getDrawable(R.drawable.rounded_corners_selected);
        mWrongBackground = getResources().getDrawable(R.drawable.rounded_corners_wrong);
        mRightBackground = getResources().getDrawable(R.drawable.rounded_corners_right);
    }

    private void nextQuestion() {
        mCurrentQuestion = mQuestionProvider.getRandomQuestion();
        setColorToAnswers(mNeutralBackground);
        mSelectedAnswer = null;
    }

    private void initNewQuestion() {
        nextQuestion();

        mQuestionTextView.setText(mCurrentQuestion.getQuestion());
        mAnswer1TextView.setText("A. " + mCurrentQuestion.getAnswer1().getText());
        mAnswer2TextView.setText("B. " + mCurrentQuestion.getAnswer2().getText());
        mAnswer3TextView.setText("C. " + mCurrentQuestion.getAnswer3().getText());
        mAnswer4TextView.setText("D. " + mCurrentQuestion.getAnswer4().getText());

        mAnswer1TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAnswer(view);
            }
        });
        mAnswer2TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAnswer(view);
            }
        });
        mAnswer3TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAnswer(view);
            }
        });
        mAnswer4TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAnswer(view);
            }
        });
        mClickable = true;
    }

    private void selectAnswer(View view) {
        if (!mClickable) {
            return;
        }

        int id = view.getId();

        if (id == R.id.answer1TextView) {
            mSelectedAnswer = mCurrentQuestion.getAnswer1();
        } else if (id == R.id.answer2TextView) {
            mSelectedAnswer = mCurrentQuestion.getAnswer2();
        } else if (id == R.id.answer3TextView) {
            mSelectedAnswer = mCurrentQuestion.getAnswer3();
        } else if (id == R.id.answer4TextView) {
            mSelectedAnswer = mCurrentQuestion.getAnswer4();
        }
        setColorToAnswers(mNeutralBackground);
        ((TextView) view).setBackground(mSelectedBackground);
    }

    private void setColorToAnswers(Drawable drawable) {
        mAnswer1TextView.setBackground(drawable);
        mAnswer2TextView.setBackground(drawable);
        mAnswer3TextView.setBackground(drawable);
        mAnswer4TextView.setBackground(drawable);
    }

    private void handleCheckDelay() {
        mClickable = false;
        final ProgressDialog mProgressDialog = ProgressDialog.show(MainActivity.this, getResources().getString(R.string.checking_answers),
                getResources().getString(R.string.please_wait), false);
        mProgressDialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgressDialog.dismiss();
                check();
            }
        }, 3000);
    }

    private void nextQuestionDelay() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mNextQuestionButton.setVisibility(View.VISIBLE);
            }
        }, 3000);
    }

    private void check() {
        mResultInfoTextView.setVisibility(View.VISIBLE);
        if (mSelectedAnswer.getCorrect()) {
            mResultInfoTextView.setText(getResources().getString(R.string.good_answer));
        } else {
            mResultInfoTextView.setText(getResources().getString(R.string.wrong_answer));
        }
        colorRightAnswer();
        nextQuestionDelay();
    }

    private void colorRightAnswer() {
        if (mCurrentQuestion.getAnswer1().getCorrect()) {
            mAnswer1TextView.setBackground(mRightBackground);
        } else {
            mAnswer1TextView.setBackground(mWrongBackground);
        }
        if (mCurrentQuestion.getAnswer2().getCorrect()) {
            mAnswer2TextView.setBackground(mRightBackground);

        } else {
            mAnswer2TextView.setBackground(mWrongBackground);
        }
        if (mCurrentQuestion.getAnswer3().getCorrect()) {
            mAnswer3TextView.setBackground(mRightBackground);

        } else {
            mAnswer3TextView.setBackground(mWrongBackground);
        }
        if (mCurrentQuestion.getAnswer4().getCorrect()) {
            mAnswer4TextView.setBackground(mRightBackground);

        } else {
            mAnswer4TextView.setBackground(mWrongBackground);
        }
    }

    public void checkAnswer(View view) {
        if (!mClickable || mSelectedAnswer == null) {
            return;
        }
        handleCheckDelay();
    }

    public void nextQuestion(View view) {
        mNextQuestionButton.setVisibility(View.GONE);
        mResultInfoTextView.setVisibility(View.GONE);
        initNewQuestion();
    }


}
