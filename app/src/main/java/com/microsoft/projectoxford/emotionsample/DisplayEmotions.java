package com.microsoft.projectoxford.emotionsample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class DisplayEmotions extends ActionBarActivity {

    TextView happinessTV,neutralTV,sadnessTV,disgustTV,fearTV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_emotions);
        happinessTV = (TextView)findViewById(R.id.happiness_val);
        neutralTV = (TextView)findViewById(R.id.neutral_val);
        sadnessTV = (TextView)findViewById(R.id.sadness_val);
        disgustTV = (TextView)findViewById(R.id.disgust_val);
        fearTV = (TextView)findViewById(R.id.fear_val);




    }

    @Override
    protected void onStart() {
        super.onStart();
        happinessTV.setText(String.valueOf(Utility.HAPPINESS));
        neutralTV.setText(String.valueOf(Utility.NEUTRAL));
        sadnessTV.setText(String.valueOf(Utility.SADNESS));
        disgustTV.setText(String.valueOf(Utility.DISGUST));
        fearTV.setText(String.valueOf(Utility.FEAR));




    }
}
