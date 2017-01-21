package com.microsoft.projectoxford.emotionsample;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FeelingsRecord extends ActionBarActivity {

    Button evaluateButton;
    EditText textEnter;
    public static double sentimentValue = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings_record);

        textEnter = (EditText)findViewById(R.id.editText);
        evaluateButton  = (Button)findViewById(R.id.evaluate_words);

        evaluateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sendText = textEnter.getText().toString();
                Intent intent = new Intent(FeelingsRecord.this,TextAnalysisService.class);
                intent.putExtra(Intent.EXTRA_TEXT,sendText);
                startService(intent);

                Log.e("FeelingsRecord","Called AnalysisService");
            }
        });



        IntentFilter intentFilter = new IntentFilter(TextAnalysisService.ACTION_FINISHEDTEXT);

        BroadcastReceiver receiver;




    }

}
