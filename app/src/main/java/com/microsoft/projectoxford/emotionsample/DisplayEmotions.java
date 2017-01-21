package com.microsoft.projectoxford.emotionsample;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayEmotions extends ActionBarActivity {

    TextView happinessTV,neutralTV,sadnessTV,disgustTV,fearTV;
    private Button sendTherapist;

    private String POST_URL = "http://192.168.1.67/WellnessDashboard/getdata.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_emotions);
        happinessTV = (TextView)findViewById(R.id.happiness_val);
        neutralTV = (TextView)findViewById(R.id.neutral_val);
        sadnessTV = (TextView)findViewById(R.id.sadness_val);
        disgustTV = (TextView)findViewById(R.id.disgust_val);
        fearTV = (TextView)findViewById(R.id.fear_val);
        sendTherapist = (Button)findViewById(R.id.button_therapist);
        happinessTV.setText(String.valueOf(Utility.HAPPINESS));
        neutralTV.setText(String.valueOf(Utility.NEUTRAL));
        sadnessTV.setText(String.valueOf(Utility.SADNESS));
        disgustTV.setText(String.valueOf(Utility.DISGUST));
        fearTV.setText(String.valueOf(Utility.FEAR));


        sendTherapist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DisplayEmotions.this, "Data Sent!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DisplayEmotions.this,SendTherapist.class);
                startService(intent);



                /*GetDataAsync getDataAsync = new GetDataAsync();
                getDataAsync.execute();
                */
            }
        });




    }




    @Override
    protected void onStart() {
        super.onStart();





    }


    private class GetDataAsync extends AsyncTask<Void,String,String>
    {


        @Override
        protected String doInBackground(Void... voids) {



            String response =  HttpRequest.post(POST_URL).body();
            Log.e("Service",response);
            return null;
        }
    }



}
