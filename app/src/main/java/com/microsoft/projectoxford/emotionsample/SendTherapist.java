package com.microsoft.projectoxford.emotionsample;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by santh on 1/21/2017.
 */

public class SendTherapist extends IntentService {

    public static String UserName = "Santhosh";

     String POST_URL = "http://10.0.2.2/WellnessDashboard/postdata.php";


    public SendTherapist()
    {
        super("SendTherapist");
    }
    public SendTherapist(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try {

            HashMap<String, String> data = new HashMap<String, String>();

            data.put("username", UserName);
            data.put("anger", String.valueOf(Utility.ANGER));
            data.put("contempt", String.valueOf(Utility.CONTEMPT));
            data.put("disgust", String.valueOf(Utility.DISGUST));
            data.put("fear", String.valueOf(Utility.FEAR));
            data.put("happy", String.valueOf(Utility.HAPPINESS));
            data.put("neutral", String.valueOf(Utility.NEUTRAL));
            data.put("sadness", String.valueOf(Utility.SADNESS));
            data.put("surprise", String.valueOf(Utility.SURPRISE));
            data.put("scale", String.valueOf(FeelingsRecord.sentimentValue));


            HttpRequest.post(POST_URL).form(data).created();


            Log.e("Service", "Inserted Values");
        }
        catch(Exception e)
        {
            showToast("Something went wrong - Perhaps the network");
            Log.e("GetData",e.getMessage());
        }





    }

    public void showToast(String message) {
        final String msg = message;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
