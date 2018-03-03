package com.example.myapplication;

import android.app.Activity;
import android.widget.TextView;
import android.os.Bundle;
import com.example.myapplication.R;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

  private EditText mEditText;
  private TextView mTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    init();
  }
  
  private void init(){
    mTextView = (TextView)findViewById(R.id.resultTextView);
    mEditText = (EditText)findViewById(R.id.inputEditText);
  }
  
  public void check(View view){
    String text = mEditText.getText().toString();
    
    String reverse = new StringBuffer(text).reverse().toString();
 
    if(text.equals(reverse)){
      mTextView.setText ("Palindrome!!!");
    }else {
      mTextView.setText("Its not palidrome!!!");
    }
  }

}
