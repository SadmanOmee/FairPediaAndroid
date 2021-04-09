package com.example.omeepc.fairpedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NormalUserProfile extends AppCompatActivity {
    private Button btn_lout;
    TextView textViewabt, textViewbct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user_profile);
        textViewabt = (TextView)findViewById(R.id.textViewab);
        textViewbct = (TextView)findViewById(R.id.textViewbc);
        Bundle tb = getIntent().getExtras();
        String ab = tb.getString("nm");
        String bc = tb.getString("ct");
        textViewabt.setText(ab);
        textViewbct.setText(bc);

        btn_lout = (Button)findViewById(R.id.lout);
        btn_lout.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                       finish();
                    }

                }
        );
    }
}
