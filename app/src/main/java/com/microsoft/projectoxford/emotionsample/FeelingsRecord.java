package com.microsoft.projectoxford.emotionsample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FeelingsRecord extends ActionBarActivity {

    Button evaluateButton;
    Button processButton;
    EditText textEnter;
    public static double sentimentValue = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings_record);

        textEnter = (EditText)findViewById(R.id.editText);
        evaluateButton  = (Button)findViewById(R.id.evaluate_words);
        processButton = (Button)findViewById(R.id.process_words);

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

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(sentimentValue==0.0)
                {
                    Toast.makeText(getApplicationContext(),"Could not complete evaluation - Time out",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Evaluation Complete: "+String.valueOf(sentimentValue), Toast.LENGTH_SHORT).show();

                }
            }
        };

        registerReceiver(receiver,intentFilter);

        processButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(FeelingsRecord.this,DisplayJournalFeelings.class);
                startActivity(intent);
            }
        });




    }

}
