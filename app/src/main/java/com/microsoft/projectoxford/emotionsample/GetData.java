package com.microsoft.projectoxford.emotionsample;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by santh on 1/21/2017.
 */

public class GetData extends IntentService {

    private String POST_URL = "http:/192.168.1.67/WellnessDashboard/getdata.php";
    public GetData()
    {
        super("GetData");
    }
    public GetData(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String response =  HttpRequest.post(POST_URL).body();
        Log.e("Service",response);


    }
}
