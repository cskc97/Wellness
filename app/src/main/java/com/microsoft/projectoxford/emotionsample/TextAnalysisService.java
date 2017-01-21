package com.microsoft.projectoxford.emotionsample;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by santh on 1/21/2017.
 */

public class TextAnalysisService extends IntentService {


    String URL = "https://westus.api.cognitive.microsoft.com/text/analytics/v2.0/sentiment";
    public static String TextAnalyticsKey = "b5582910d9b242a7a97f3de4d3707634";

    public static String ACTION_FINISHEDTEXT = "finished";


    public  TextAnalysisService()
    {
        super("TextAnalysisService");
    }
    public TextAnalysisService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.e("Service","Inside");

        String stuff = intent.getStringExtra(Intent.EXTRA_TEXT);


        JSONObject object = new JSONObject();

        try {
            object.put("language","en");
            object.put("id","01");
            object.put("text",stuff);



        } catch (JSONException e) {
            e.printStackTrace();
        }


        JSONArray array = new JSONArray();
        try {
            array.put(0,object);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject mainObjectPut = new JSONObject();
        try {
            mainObjectPut.put("documents",array);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //  array.put
        String postString = mainObjectPut.toString();

        Log.e("Service",postString);




        String response = HttpRequest.post(URL).contentType("application/json").
                header("Ocp-Apim-Subscription-Key", TextAnalyticsKey).
                send(postString).body();

        Log.e("Service",response);




        JSONObject parseJSOMain = null;
        try {
            parseJSOMain = new JSONObject(response);
           JSONArray arrayanother= parseJSOMain.getJSONArray("documents");
            JSONObject anotherObject = (JSONObject) arrayanother.get(0);
            String finalValue = anotherObject.getString("score");
            Log.e("Sentiment",finalValue);

            FeelingsRecord.sentimentValue = Double.valueOf(finalValue);

            Intent sendBroadcastintent = new Intent(TextAnalysisService.ACTION_FINISHEDTEXT);
            sendBroadcast(sendBroadcastintent);






        } catch (JSONException e) {
            e.printStackTrace();
        }







    }
}
