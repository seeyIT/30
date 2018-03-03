package com.kornel_ius.dotcrossvector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mFirstX;
    private EditText mFirstY;
    private EditText mFirstZ;
    private EditText mSecondX;
    private EditText mSecondY;
    private EditText mSecondZ;
    private TextView mDotProductResult;
    private TextView mCrossProductResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        init();
    }

    private void init() {
        mFirstX = (EditText) findViewById(R.id.firstX);
        mFirstY = (EditText) findViewById(R.id.firstY);
        mFirstZ = (EditText) findViewById(R.id.firstZ);
        mSecondX = (EditText) findViewById(R.id.secondX);
        mSecondY = (EditText) findViewById(R.id.secondY);
        mSecondZ = (EditText) findViewById(R.id.secondZ);
        mDotProductResult = (TextView) findViewById(R.id.dotProduct);
        mCrossProductResult = (TextView) findViewById(R.id.crossProduct);
    }

    private String calcCrossProduct(int x1, int y1, int z1, int x2, int y2, int z2) {
        return "X: " + (y1 * z2 - z1 * y2) + " Y: " + (z1 * x2 - x1 * z2) + " Z: " + (x1 * y2 - y1 * x2);
    }

    private String calcDotProduct(int x1, int y1, int z1, int x2, int y2, int z2) {
        return String.valueOf(x1 * x2 + y1 * y2 + z1 * z2);
    }


    public void calculate(View view) {
        int x1 = Integer.parseInt(mFirstX.getText().toString());
        int y1 = Integer.parseInt(mFirstY.getText().toString());
        int z1 = Integer.parseInt(mFirstZ.getText().toString());
        int x2 = Integer.parseInt(mSecondX.getText().toString());
        int y2 = Integer.parseInt(mSecondY.getText().toString());
        int z2 = Integer.parseInt(mSecondZ.getText().toString());

        mDotProductResult.setText(getResources().getString(R.string.dot_product) + " " + calcDotProduct(x1, y1, z1, x2, y2, z2));
        mCrossProductResult.setText(getResources().getString(R.string.cross_product) + " " + calcCrossProduct(x1, y1, z1, x2, y2, z2));
    }

}
