package com.example.omeepc.fairpedia;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {
    private static Button btn_tologin, btn_tonewaccount;
    EditText edttxt_toemail, edttxt_topassword;
    String emailid = "";
    String passw = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_tologin = (Button) findViewById(R.id.btn_login);
        btn_tonewaccount = (Button) findViewById(R.id.btn_newaccount);
        edttxt_toemail = (EditText) findViewById(R.id.edttxt_email);
        edttxt_topassword = (EditText) findViewById(R.id.edttxt_password);
        //afterClickOnLoginPage();
    }

    public void afterClickOnLoginButton(View v)
    {
        String emailid = edttxt_toemail.getText().toString();
        String passw = edttxt_topassword.getText().toString();
        String type  = "login";
        BackgroundWorkerLgin backgroundWorkerLgin = new BackgroundWorkerLgin(this);
        backgroundWorkerLgin.execute(type, emailid, passw);


        /*String bwresult = BackgroundWorker.rs;
         if(bwresult.equals("login success"))
         {
              BackgroundWorker.rs = "login not success";
              //Intent intent  = new Intent("com.example.omeepc.fairpedia.NormalUserProfile");
              Intent intent  = new Intent(MainActivity.this, NormalUserProfile.class);
              startActivity(intent);
              }
         else if(bwresult.equals("login not success"))
         {
              Toast.makeText(getApplicationContext(),"Account doesn't exist!",Toast.LENGTH_SHORT).show();
              BackgroundWorker.rs = "login success";
         }*/
    }
    public void afterClickOnRegisterButton(View v)
    {
        Intent intent  = new Intent(MainActivity.this, SignUpType.class);
        startActivity(intent);
    }

            class BackgroundWorkerLgin extends AsyncTask<String,Void,String> {
            Context contextlgin;
            AlertDialog alertDialog;
            //public static String rs = "";
            BackgroundWorkerLgin(Context ctx)
            {
                contextlgin = ctx;
            }

            @Override
            protected String doInBackground(String... params) {
                String type = params[0];
                String login_url = "http://192.168.0.100/login.php";
                //String login_url = "http://192.168.0.118/login.php";
                if(type.equals("login"))
                {
                    try {
                        emailid = params[1];
                        passw = params[2];

                        URL url = new URL(login_url);
                        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);

                        OutputStream outputStream  = httpURLConnection.getOutputStream();
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                        String post_data = URLEncoder.encode("emailid","UTF-8")+"="+URLEncoder.encode(emailid,"UTF-8")+"&"+URLEncoder.encode("passw","UTF-8")+"="+URLEncoder.encode(passw,"UTF-8");
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

                if(result.equals("normal user login success"))
                {
                    //BackgroundWorker.rs = "login not success";
                    //Intent intent  = new Intent("com.example.omeepc.fairpedia.NormalUserProfile");

                    Intent intenta  = new Intent(MainActivity.this, NormalUserProfileAndStuffs.class);
                    intenta.putExtra("emailid", emailid);
                    intenta.putExtra("passw",passw);
                    startActivity(intenta);
                }
                else if(result.equals("authority login success"))
                {
                    Intent intenta  = new Intent(MainActivity.this, AuthorityProfileAndStuffs.class);
                    intenta.putExtra("emailid", emailid);
                    intenta.putExtra("passw",passw);
                    startActivity(intenta);
                    //Toast.makeText(getApplicationContext(),"Accdfdsfefeeeount doesn't exist!",Toast.LENGTH_SHORT).show();
                }
                else if(result.equals("host login success"))
                {
                    Intent intenta  = new Intent(MainActivity.this, HostProfileAndStuff.class);
                    intenta.putExtra("emailid", emailid);
                    intenta.putExtra("passw",passw);
                    startActivity(intenta);
                    //Toast.makeText(getApplicationContext(),"Accdfdsfefeeeount doesn't exist!",Toast.LENGTH_SHORT).show();
                }
                else if(result.equals("login not success"))
                {
                    Toast.makeText(getApplicationContext(),"Account doesn't exist!",Toast.LENGTH_SHORT).show();
                    //BackgroundWorker.rs = "login success";
                }

            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }
        }
}
