package com.example.omeepc.fairpedia;

import android.content.Context;
import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by OMEE PC on 18/1/2018.
 */

public class Parser extends AsyncTask<Void, Integer, Integer>{
    Context cntx;
    String data;
    String nm, el, gn, bt, pf, ar, zc, mn;
    ArrayList<String> arra = new ArrayList<>();
    ListView lv;


    public Parser(Context cntx, String data, String nm, String el, String gn, String bt, String pf, String ar, String zc, String mn) {
        this.cntx = cntx;
        this.data = data;
        this.nm = nm;
        this.el = el;
        this.gn = gn;
        this.bt = bt;
        this.pf = pf;
        this.ar = ar;
        this.zc = zc;
        this.mn = mn;
    }

    /*public Parser(Context cntx, String data, ListView lv) {
        this.cntx = cntx;
        this.data = data;
        this.lv = lv;
    }*/

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parse();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if(integer == 1)
        {

        }
        else
        {
            Toast.makeText(cntx,"Account doesn't exist!",Toast.LENGTH_SHORT).show();
        }
    }
    private int parse()
    {
        try {
            JSONArray ja = new JSONArray(data);
            JSONObject jo = null;
            //Toast.makeText(cntx,"fgejetjte exikukust!",Toast.LENGTH_SHORT).show();

            //arra.clear();
            for(int i=0; i<ja.length(); i++)
            {
                jo = new JSONObject(data);
                //jo = ja.getJSONObject(i);
                ja = jo.getJSONArray("server_response");
                //Toast.makeText(cntx,"fgejetjte exikukust!",Toast.LENGTH_LONG).show();
                nm = jo.getString("user_first_name") + " " + jo.getString("user_last_name");
                el = jo.getString("user_email_address");
                gn = jo.getString("user_gender");
                bt = jo.getString("user_birthdate");
                pf = jo.getString("user_profession");
                ar =jo.getString("user_country") + " " + jo.getString("user_city");
                zc = jo.getString("user_zipcode");
                mn = jo.getString("user_mobileno");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Toast.makeText(cntx,"fgejetjte exikukust!",Toast.LENGTH_LONG).show();
        return 0;
    }


}
