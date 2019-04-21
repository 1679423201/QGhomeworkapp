package com.example.qghomeworkapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class myE extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_e);
        Intent intent = getIntent();
        String word_v = intent.getStringExtra("word_v");
        textView = (TextView)findViewById(R.id.word_view);
        sendRequestWithOkHttp(word_v);
    }
    private void sendRequestWithOkHttp(final String word_v){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://fanyi.youdao.com/translate?&doctype=json&type=AUTO&i="+word_v).build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    pareJSONWithISONObject(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void pareJSONWithISONObject(String jsonData){
        try{
            JSONObject jsonObiect = new JSONObject(jsonData);
            String translateResult = jsonObiect.getString("translateResult");
            JSONArray word = new JSONArray(translateResult);
            JSONArray word2 = word.getJSONArray(0);
            for (int i = 0; i <word.length() ; i++) {
                JSONObject mains = word2.getJSONObject(i);
                String Chin = mains.getString("tgt");
                showWord(Chin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void showWord(final String word){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(word);
            }
        });
    }
}
