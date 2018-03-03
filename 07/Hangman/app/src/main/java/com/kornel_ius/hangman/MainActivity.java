package com.kornel_ius.hangman;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String LETTERS = "QWERTYUIOPLKJHGFDSAZXCVBNM";
    private ImageView mMainImage;
    private TextView mTextView;
    private String mWordToGuess;
    private int mGuesses[];
    private ArrayList<Button> mPressedButtons = new ArrayList<>();
    private int mLifes = 5;
    private Drawable mImages[] = new Drawable[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        init();
        startNewGame();
    }

    private void init() {
        mMainImage = (ImageView) findViewById(R.id.image);
        mTextView = (TextView) findViewById(R.id.text);
        mImages[0] = ContextCompat.getDrawable(MainActivity.this, R.drawable.i0);
        mImages[1] = ContextCompat.getDrawable(MainActivity.this, R.drawable.i1);
        mImages[2] = ContextCompat.getDrawable(MainActivity.this, R.drawable.i2);
        mImages[3] = ContextCompat.getDrawable(MainActivity.this, R.drawable.i3);
        mImages[4] = ContextCompat.getDrawable(MainActivity.this, R.drawable.i4);
        mImages[5] = ContextCompat.getDrawable(MainActivity.this, R.drawable.i5);
    }

    private void displayWord() {
        String wordDisplay = "";
        for (int i = 0; i < mGuesses.length; ++i) {
            if (mGuesses[i] == 1) {
                wordDisplay = wordDisplay + mWordToGuess.charAt(i) + " ";
            } else {
                wordDisplay = wordDisplay + " _ ";
            }
        }
        mTextView.setText(wordDisplay);
    }

    private void checkLetter(char letter) {
        boolean exist = false;
        for (int i = 0; i < mWordToGuess.length(); ++i) {
            if (Character.toUpperCase(mWordToGuess.charAt(i)) == letter) {
                mGuesses[i] = 1;
                exist = true;
            }
        }
        displayWord();
        if (!exist) {
            incorrectLetter();
        }
    }

    private void incorrectLetter() {
        --mLifes;
        changeImage(mLifes);
        if (mLifes == 0) {
            toast(getResources().getString(R.string.lost));
            newGameTimer();
        }
    }

    private void changeImage(int lifes) {
        mMainImage.setImageDrawable(mImages[lifes]);
    }

    private void randomWord() {
        Random r = new Random();
        int length = (r.nextInt(4) + 5);
        mWordToGuess = "";
        for (int i = 0; i < length; ++i) {
            int random = r.nextInt(26);
            mWordToGuess = mWordToGuess + LETTERS.charAt(random);
        }
        mGuesses = new int[length];
        Arrays.fill(mGuesses, 0);
    }

    private void startNewGame() {
        randomWord();
        mLifes = 5;
        changeImage(mLifes);
        restartButtons();
        displayWord();
    }

    private void checkWin() {
        for (int i = 0; i < mGuesses.length; ++i) {
            if (mGuesses[i] == 0) {
                return;
            }
        }
        toast(getResources().getString(R.string.win));
        newGameTimer();
    }

    private void restartButtons() {
        for (int i = 0; i < mPressedButtons.size(); ++i) {
            mPressedButtons.get(i).setEnabled(true);
        }
        mPressedButtons.clear();
    }

    public void letterPress(View view) {
        Button button = (Button) view;
        char letter = button.getText().toString().charAt(0);
        checkLetter(letter);
        button.setEnabled(false);
        checkWin();
        mPressedButtons.add(button);
    }

    private void toast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    private void newGameTimer() {
        Handler handler = new Handler();
        toast(getResources().getString(R.string.new_game));
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startNewGame();
            }
        }, 5000);
    }
}
