package com.kornel_ius.prsls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mResultTextView;
    private Random mRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        init();
    }

    private void init() {
        mRandom = new Random();
        mResultTextView = (TextView) findViewById(R.id.resultTextView);
    }

    private int randomComputerMove() {
        return mRandom.nextInt(5) + 1;
    }

    private int checkWin(int player, int computer) {
        Log.e("player", player + "");
        Log.e("conputer", computer + "");
        if (player == 1) {
            if (computer == 1) {
                return 0;
            }
            if (computer == 2 || computer == 4) {
                return 1;
            }
            return -1;
        }
        if (player == 2) {
            if (computer == 2) {
                return 0;
            }
            if (computer == 3 || computer == 5) {
                return 1;
            }
            return -1;
        }
        if (player == 3) {
            if (computer == 3) {
                return 0;
            }
            if (computer == 4 || computer == 1) {
                return 1;
            }
            return -1;
        }
        if (player == 4) {
            if (computer == 4) {
                return 0;
            }
            if (computer == 5 || computer == 2) {
                return 1;
            }
            return -1;
        }
        if (player == 5) {
            if (computer == 5) {
                return 0;
            }
            if (computer == 1 || computer == 3) {
                return 1;
            }
            return 0;
        }
        return -2;
    }

    private String getResult(int player, int computer) {
        int result = checkWin(player, computer);
        String stringResult = "";
        if (result == 1) {
            stringResult = getResources().getString(R.string.win);
        } else if (result == -1) {
            stringResult = getResources().getString(R.string.lose);
        } else if (result == 0) {
            stringResult = getResources().getString(R.string.draw);
        } else {
            stringResult = getResources().getString(R.string.error);
        }
        return stringResult;
    }

    public void select(View view) {
        int tag = Integer.valueOf(String.valueOf(((Button) view).getTag()));
        int computer = randomComputerMove();
        mResultTextView.setText(getResult(tag, computer));
    }

}
