package com.kornel_ius.anagramchecker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mInputOneEditText;
    private EditText mInputTwoEditText;
    private TextView mOutputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mInputOneEditText = (EditText) findViewById(R.id.inputOneEditText);
        mInputTwoEditText = (EditText) findViewById(R.id.inputTwoEditText);
        mOutputTextView = (TextView) findViewById(R.id.outputTextView);
    }


    private boolean stringsEqual(String one, String two) {
        ArrayList<String> first = new ArrayList<>();
        ArrayList<String> second = new ArrayList<>();

        for (int i = 0; i < one.length(); ++i) {
            String letter = String.valueOf(one.charAt(i));
            if (letter.equals(" ") || letter.equals("\n") || letter.equals(",") || letter.equals(".") || letter.equals("-") || letter.equals("'")) {
                continue;
            }
            first.add(letter.toLowerCase());
        }
        for (int i = 0; i < two.length(); ++i) {
            String letter = String.valueOf(two.charAt(i));
            if (letter.equals(" ") || letter.equals("\n") || letter.equals(",") || letter.equals(".") || letter.equals("-") || letter.equals("'")) {
                continue;
            }
            second.add(letter.toLowerCase());
        }
        Collections.sort(first);
        Collections.sort(second);

        if (first.size() != second.size()) {
            return false;
        }
        for (int i = 0; i < first.size(); ++i) {
            if (!first.get(i).equals(second.get(i))) {
                return false;
            }
        }
        return true;
    }

    public void check(View view) {
        String one = mInputOneEditText.getText().toString();
        String two = mInputTwoEditText.getText().toString();

        if (one.length() == 0 || two.length() == 0) {
            return;
        }
        if (stringsEqual(one, two)) {
            mOutputTextView.setText(getResources().getText(R.string.anagram));
        } else {
            mOutputTextView.setText(getResources().getText(R.string.no_anagram));
        }
    }
}
