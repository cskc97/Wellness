package com.microsoft.projectoxford.emotionsample;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by santh on 1/21/2017.
 */

public class SendTherapist extends IntentService {

    public static String UserName = "Santhosh";

     String POST_URL = "http://192.168.1.67/WellnessDashboard/postdata.php";


    public SendTherapist()
    {
        super("SendTherapist");
    }
    public SendTherapist(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        HashMap<String,String> data = new HashMap<String,String>();

        data.put("username",UserName);
        data.put("anger",String.valueOf(Utility.ANGER));
        data.put("contempt",String.valueOf(Utility.CONTEMPT));
        data.put("disgust",String.valueOf(Utility.DISGUST));
        data.put("fear",String.valueOf(Utility.FEAR));
        data.put("happy",String.valueOf(Utility.HAPPINESS));
        data.put("neutral",String.valueOf(Utility.NEUTRAL));
        data.put("sadness",String.valueOf(Utility.SADNESS));
        data.put("surprise",String.valueOf(Utility.SURPRISE));
        data.put("scale",String.valueOf(FeelingsRecord.sentimentValue));



         HttpRequest.post(POST_URL).form(data).created();







        Log.e("Service","Inserted Values");





    }
}
