package com.kornel_ius.tictactoe;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Button> mButtonList = new ArrayList<>();
    private TextView mMoveInformation;
    private String mBoard[] = new String[9];
    private String mMove = "X";
    private boolean mGameOn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        init();
        clearBoard();
    }

    private void init() {
        mMoveInformation = (TextView) findViewById(R.id.moveInformationTextView);
        mButtonList.add((Button) findViewById(R.id.button0x0));
        mButtonList.add((Button) findViewById(R.id.button0x1));
        mButtonList.add((Button) findViewById(R.id.button0x2));
        mButtonList.add((Button) findViewById(R.id.button1x0));
        mButtonList.add((Button) findViewById(R.id.button1x1));
        mButtonList.add((Button) findViewById(R.id.button1x2));
        mButtonList.add((Button) findViewById(R.id.button2x0));
        mButtonList.add((Button) findViewById(R.id.button2x1));
        mButtonList.add((Button) findViewById(R.id.button2x2));

    }

    private void checkWin() {
        if (mBoard[0].equals(mBoard[1]) && mBoard[1].equals(mBoard[2]) && !mBoard[2].equals("")) {
            win(mMove);
        } else if (mBoard[3].equals(mBoard[4]) && mBoard[4].equals(mBoard[5]) && !mBoard[5].equals("")) {
            win(mMove);

        } else if (mBoard[6].equals(mBoard[7]) && mBoard[7].equals(mBoard[8]) && !mBoard[8].equals("")) {
            win(mMove);

        } else if (mBoard[0].equals(mBoard[4]) && mBoard[4].equals(mBoard[8]) && !mBoard[8].equals("")) {
            win(mMove);

        } else if (mBoard[2].equals(mBoard[4]) && mBoard[4].equals(mBoard[6]) && !mBoard[6].equals("")) {
            win(mMove);

        } else if (mBoard[0].equals(mBoard[3]) && mBoard[3].equals(mBoard[6]) && !mBoard[6].equals("")) {
            win(mMove);

        } else if (mBoard[1].equals(mBoard[4]) && mBoard[4].equals(mBoard[7]) && !mBoard[7].equals("")) {
            win(mMove);

        } else if (mBoard[2].equals(mBoard[5]) && mBoard[5].equals(mBoard[8]) && !mBoard[8].equals("")) {
            win(mMove);

        }
    }

    private void clearBoard() {
        for (int i = 0; i < 9; ++i) {
            mBoard[i] = "";
            mButtonList.get(i).setText("");
        }
    }

    private void changeMove() {
        if (mMove.equals("X")) {
            mMove = "O";
        } else {
            mMove = "X";
        }
    }

    private void win(String winner) {
        Toast.makeText(MainActivity.this, winner + " " + getResources().getString(R.string.winner), Toast.LENGTH_SHORT).show();
        mGameOn = false;
        startNewGame();
    }

    private void refreshInformation() {
        mMoveInformation.setText(getResources().getString(R.string.move) + " " + mMove);
    }

    public void fieldClick(View view) {
        if (!mGameOn) {
            return;
        }
        if (!mBoard[Integer.valueOf(view.getTag().toString())].equals("")) {
            return;
        }
        mBoard[Integer.valueOf(view.getTag().toString())] = mMove;
        ((Button) view).setText(mMove);
        checkWin();
        checkDraw();
        changeMove();
        refreshInformation();

    }

    private void checkDraw() {
        int fieldFilled = 0;
        for (int i = 0; i < 9; ++i) {
            if (mBoard[i].equals("")) {
                ++fieldFilled;

            }
        }
        if (fieldFilled == 0) {
            draw();
        }
    }

    private void draw() {
        Toast.makeText(MainActivity.this, getResources().getString(R.string.draw), Toast.LENGTH_SHORT).show();
        mGameOn = false;
        startNewGame();
    }

    private void startNewGame() {
        Toast.makeText(MainActivity.this, getResources().getString(R.string.next_game), Toast.LENGTH_SHORT).show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mGameOn = true;
                clearBoard();
                Toast.makeText(MainActivity.this, getResources().getString(R.string.play_again), Toast.LENGTH_SHORT).show();

            }
        }, 5000);
    }

}
