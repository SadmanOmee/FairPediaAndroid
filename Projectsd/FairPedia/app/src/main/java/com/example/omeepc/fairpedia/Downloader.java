package com.example.omeepc.fairpedia;

import android.content.Context;
import android.location.Address;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by OMEE PC on 18/1/2018.
 */

public class Downloader  extends AsyncTask<Void, Integer, String> {
    Context cntx;
    String address;
    String nm, el, gn, bt, pf, ar, zc, mn;
    ListView lv;

    public Downloader(Context cntx, String address, String nm, String el, String gn, String bt, String pf, String ar, String zc, String mn) {
        this.cntx = cntx;
        this.address = address;
        this.nm = nm;
        this.el = el;
        this.gn = gn;
        this.bt = bt;
        this.pf = pf;
        this.ar = ar;
        this.zc = zc;
        this.mn = mn;
    }

    /*public Downloader(Context cntx, String address, ListView lv) {
        this.cntx = cntx;
        this.address = address;
        this.lv = lv;
    }*/

    @Override
    protected String doInBackground(Void... params) {
        String data = downloadData();
        return data;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s != null)
        {
            //Toast.makeText(cntx,"Account doesn't exist!",Toast.LENGTH_SHORT).show();
            Parser par = new Parser(cntx, s, nm, el, gn, bt, pf, ar, zc, mn);
            par.execute();
        }
        else
        {
            Toast.makeText(cntx,"Accougfhkteryryrynt doesn't exist!",Toast.LENGTH_SHORT).show();
        }
    }



    private String downloadData()
    {
        InputStream is = null;
        String line = null;
        try {
            URL url = new URL(address);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            is = new BufferedInputStream(con.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuffer sb = new StringBuffer();
            if(br != null)
            {
                while((line = br.readLine()) != null)
                {
                    sb.append(line+"\n");
                }
            }
            else
            {
                return null;
            }
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is != null)
            {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
