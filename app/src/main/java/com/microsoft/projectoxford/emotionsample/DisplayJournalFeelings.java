package com.microsoft.projectoxford.emotionsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayJournalFeelings extends ActionBarActivity {

    TextView scaleTV, conclusionTV;
    static String emotionConclusion = null;
    Button sendTherapistButtonJournal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_journal_feelings);
        scaleTV = (TextView)findViewById(R.id.scale_valuetv);
        conclusionTV=(TextView)findViewById(R.id.conclusion_valuetv);

        if(FeelingsRecord.sentimentValue!=0.0) {
            setTextViews();
        }

        sendTherapistButtonJournal = (Button)findViewById(R.id.sendtherapistjournal);

        sendTherapistButtonJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Data Sent to Therapist", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DisplayJournalFeelings.this,SendTherapist.class);
                startService(intent);


            }
        });


    }









    public void setTextViews()
    {
        scaleTV.setText(String.valueOf(FeelingsRecord.sentimentValue));

        if(FeelingsRecord.sentimentValue < 0.40)
        {
            emotionConclusion = "Negative";
        }
        else if(FeelingsRecord.sentimentValue>0.60)
        {
            emotionConclusion="Positive";
        }
        else
        {
            emotionConclusion = "Neutral";
        }

        conclusionTV.setText(emotionConclusion);


    }



}
