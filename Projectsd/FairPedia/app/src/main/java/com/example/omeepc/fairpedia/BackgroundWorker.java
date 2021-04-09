package com.example.omeepc.fairpedia;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by OMEE PC on 12/1/2018.
 */

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    public static String rs = "";
    BackgroundWorker(Context ctx)
    {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        /*String registeruser_url = "http://172.20.42.48/registeruser.php";
        String registerauthority_url = "http://172.20.42.48/registerauthority.php";
        String registerhost_url = "http://172.20.42.48/registerauthority.php";*/


        String registeruser_url = "http://192.168.0.100/registeruser.php";
        String registerauthority_url = "http://192.168.0.100/registerauthority.php";
        String registerhost_url = "http://192.168.0.100/registerhost.php";

        if(type.equals("registeruser"))
        {
            try {
                String ufname = params[1];
                String ulname = params[2];
                String uemailid = params[3];
                String upassw = params[4];
                String ugndr = params[5];
                String ubdate = params[6];
                String uprof = params[7];
                String ucntry = params[8];
                String ucty = params[9];
                String uzpcd = params[10];
                String umblno = params[11];

                URL url = new URL(registeruser_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream  = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("ufname","UTF-8")+"="+URLEncoder.encode(ufname,"UTF-8")+"&"
                        +URLEncoder.encode("ulname","UTF-8")+"="+URLEncoder.encode(ulname,"UTF-8")+"&"
                        +URLEncoder.encode("uemailid","UTF-8")+"="+URLEncoder.encode(uemailid,"UTF-8")+"&"
                        +URLEncoder.encode("upassw","UTF-8")+"="+URLEncoder.encode(upassw,"UTF-8")+"&"
                        +URLEncoder.encode("ugndr","UTF-8")+"="+URLEncoder.encode(ugndr,"UTF-8")+"&"
                        +URLEncoder.encode("ubdate","UTF-8")+"="+URLEncoder.encode(ubdate,"UTF-8")+"&"
                        +URLEncoder.encode("uprof","UTF-8")+"="+URLEncoder.encode(uprof,"UTF-8")+"&"
                        +URLEncoder.encode("ucntry","UTF-8")+"="+URLEncoder.encode(ucntry,"UTF-8")+"&"
                        +URLEncoder.encode("ucty","UTF-8")+"="+URLEncoder.encode(ucty,"UTF-8")+"&"
                        +URLEncoder.encode("uzpcd","UTF-8")+"="+URLEncoder.encode(uzpcd,"UTF-8")+"&"
                        +URLEncoder.encode("umblno","UTF-8")+"="+URLEncoder.encode(umblno,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String result = "";
                String line = "";

                while((line = bufferedReader.readLine()) != null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("registerauthority"))
        {
            try {
                String ausrname = params[1];
                String aemailid = params[2];
                String apassw = params[3];
                String astllnm = params[4];
                String astlltyp = params[5];
                String afairfreq = params[6];
                String acntry = params[7];
                String acty = params[8];
                String azpcd = params[9];
                String amblno = params[10];

                URL url = new URL(registerauthority_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream  = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("ausrname","UTF-8")+"="+URLEncoder.encode(ausrname,"UTF-8")+"&"
                        +URLEncoder.encode("aemailid","UTF-8")+"="+URLEncoder.encode(aemailid,"UTF-8")+"&"
                        +URLEncoder.encode("apassw","UTF-8")+"="+URLEncoder.encode(apassw,"UTF-8")+"&"
                        +URLEncoder.encode("astllnm","UTF-8")+"="+URLEncoder.encode(astllnm,"UTF-8")+"&"
                        +URLEncoder.encode("astlltyp","UTF-8")+"="+URLEncoder.encode(astlltyp,"UTF-8")+"&"
                        +URLEncoder.encode("afairfreq","UTF-8")+"="+URLEncoder.encode(afairfreq,"UTF-8")+"&"
                        +URLEncoder.encode("acntry","UTF-8")+"="+URLEncoder.encode(acntry,"UTF-8")+"&"
                        +URLEncoder.encode("acty","UTF-8")+"="+URLEncoder.encode(acty,"UTF-8")+"&"
                        +URLEncoder.encode("azpcd","UTF-8")+"="+URLEncoder.encode(azpcd,"UTF-8")+"&"
                        +URLEncoder.encode("amblno","UTF-8")+"="+URLEncoder.encode(amblno,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String result = "";
                String line = "";

                while((line = bufferedReader.readLine()) != null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("registerhost"))
        {
            try {
                String husrname = params[1];
                String hemailid = params[2];
                String hpassw = params[3];
                String hcntry = params[4];
                String hcty = params[5];
                String hmblno = params[6];

                URL url = new URL(registerhost_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream  = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("husrname","UTF-8")+"="+URLEncoder.encode(husrname,"UTF-8")+"&"
                        +URLEncoder.encode("hemailid","UTF-8")+"="+URLEncoder.encode(hemailid,"UTF-8")+"&"
                        +URLEncoder.encode("hpassw","UTF-8")+"="+URLEncoder.encode(hpassw,"UTF-8")+"&"
                        +URLEncoder.encode("hcntry","UTF-8")+"="+URLEncoder.encode(hcntry,"UTF-8")+"&"
                        +URLEncoder.encode("hcty","UTF-8")+"="+URLEncoder.encode(hcty,"UTF-8")+"&"
                        +URLEncoder.encode("hmblno","UTF-8")+"="+URLEncoder.encode(hmblno,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String result = "";
                String line = "";

                while((line = bufferedReader.readLine()) != null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        //super.onPreExecute();
        //alertDialog = new AlertDialog.Builder(context).create();
        //alertDialog.setTitle("loginstatus");
    }

    @Override
    protected void onPostExecute(String result) {
        //super.onPostExecute(aVoid);
        //alertDialog.setMessage(result);
        //alertDialog.show();
        //Intent passintent  = new Intent("com.example.omeepc.fairpedia.MainActivity");
        //passintent.putExtra("reslt",result);
        //rs = result;

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
