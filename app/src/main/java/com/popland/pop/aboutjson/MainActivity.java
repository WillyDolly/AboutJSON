package com.popland.pop.aboutjson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<sinhVien> arrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lv = (ListView)findViewById(R.id.LV);
        arrl = new ArrayList<sinhVien>();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new DocJSON().execute();
            }
        });
    }

   class DocJSON extends AsyncTask<String,String,String> {
       @Override
       protected String doInBackground(String... params) {
           return DocNoiDung_TuURL("http://192.168.1.95/webservice/JsonDemo.php");
       }

       @Override
       protected void onPostExecute(String s) {
           super.onPostExecute(s);
           try {
               JSONArray array = new JSONArray(s);
               for(int i=0;i<array.length();i++){
                   JSONObject object = array.getJSONObject(i);
                   arrl.add(new sinhVien(object.getString("ten"),object.getInt("maso"),object.getString("lop")));
               }
               CustomBaseAdapter adapter = new CustomBaseAdapter(MainActivity.this,R.layout.custom_layout,arrl);
               lv.setAdapter(adapter);
           } catch (JSONException e) {
               e.printStackTrace();
           }
          // tv.setText(s);
       }
   }

    public String DocNoiDung_TuURL(String theurl){
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(theurl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while((line = bufferedReader.readLine())!=null){
                content.append(line+"\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
