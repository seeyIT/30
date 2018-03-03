package com.kornel_ius.storagemoney;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private final String FILE_PATH = Environment.getExternalStorageDirectory().toString() + "/StorageMoney/money.txt";
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private TextView mFileMoneyTextView;
    private TextView mTempMoneyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyStoragePermissions(this);
        init();
        checkIfFileExist();
        refreshFileMoney();
    }

    private void init() {
        mFileMoneyTextView = (TextView) findViewById(R.id.fileMoneyTextView);
        mTempMoneyTextView = (TextView) findViewById(R.id.tempMoneyTextView);
    }

    private void checkIfFileExist() {
        File directory = new File(Environment.getExternalStorageDirectory() + "/StorageMoney");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
                writeMoney(0);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.e("exist", file.exists() + "");


    }

    private void refreshFileMoney() {
        String money = readMoney();
        mFileMoneyTextView.setText(money);

    }

    private String readMoney() {
        String money = "";

        File file = new File(FILE_PATH);

        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
            }
            br.close();
        } catch (IOException e) {
        }

        return text.toString();
    }

    private void writeMoney(int money) {
        String moneyString = String.valueOf(money);
        try {
            FileOutputStream fOut = new FileOutputStream(FILE_PATH);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(moneyString);

            myOutWriter.close();

            fOut.flush();
            fOut.close();
        } catch (IOException e) {
        }
    }


    public void changeAmount(View view) {
        int currentMoney = Integer.parseInt(mTempMoneyTextView.getText().toString());

        currentMoney = currentMoney + Integer.parseInt(((Button) view).getText().toString());
        mTempMoneyTextView.setText(currentMoney + "");
    }

    public void submit(View view) {
        int fileMoney = 0;
        try {
            String money = readMoney();
            fileMoney = Integer.parseInt(money);
        } catch (Exception e) {
            fileMoney = 0;
        }
        fileMoney = fileMoney + Integer.parseInt(mTempMoneyTextView.getText().toString());
        writeMoney(fileMoney);
        refreshFileMoney();
    }

    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        ActivityCompat.requestPermissions(
                activity,
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
        );
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}
