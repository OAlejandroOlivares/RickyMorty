package com.example.flinkTest;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

class MyAsyncTask extends AsyncTask<Void, Void, JSONArray> {
    private Context context;
    private characterListFragmet fragmet;

    public MyAsyncTask(Context context, characterListFragmet fragmet) {
        this.context = context;
        this.fragmet = fragmet;
    }

    @Override
    protected JSONArray doInBackground(Void... voids) {
        //TODO Use HttpURLconection por que en las notas decia que no debia usar muchas librerias de terceros, si no hubiera usado retrofit, o en su defecto okhttp
        StringBuilder sbr  = new StringBuilder(context.getString(R.string.baseUrl));
        URL url;
        JSONArray result = null;
        HttpURLConnection conn = null;
        try{
            url = new URL(sbr.toString());
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.connect();
            InputStream in = new BufferedInputStream(conn.getInputStream());
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                int c = in.read();
                StringBuilder sb = new StringBuilder();
                while (c != -1) {
                    sb.append((char) c);
                    c = in.read();
                }
                JSONObject response = new JSONObject(String.valueOf(sb));
                result = response.getJSONArray("results");
                Log.d("respuesta",result.toString());
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        super.onPostExecute(jsonArray);
        if(fragmet != null){
            fragmet.populateList(jsonArray);
        }
    }
}
