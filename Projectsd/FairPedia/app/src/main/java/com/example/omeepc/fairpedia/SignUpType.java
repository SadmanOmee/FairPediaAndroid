package com.example.omeepc.fairpedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SignUpType extends AppCompatActivity {
    private static RadioGroup radgrp_tousertype;
    private static RadioButton radbtn_touser;
    private static Button btn_tonext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_type);
        selectTypeOfUser();
    }
    public void selectTypeOfUser() {
        radgrp_tousertype = (RadioGroup) findViewById(R.id.radgrp_usertype);
        btn_tonext = (Button) findViewById(R.id.btn_next);
        btn_tonext.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        int selected_radbtn = radgrp_tousertype.getCheckedRadioButtonId();
                        radbtn_touser = (RadioButton) findViewById(selected_radbtn);

                        if(radbtn_touser.getText().toString().equals("Normal User"))
                        {
                            Intent intent  = new Intent("com.example.omeepc.fairpedia.SignUpUser");
                            startActivity(intent);
                        }
                        else if(radbtn_touser.getText().toString().equals("Authority"))
                        {
                            Intent intent  = new Intent("com.example.omeepc.fairpedia.SignUpAuthority");
                            startActivity(intent);
                        }
                        else if(radbtn_touser.getText().toString().equals("Host"))
                        {
                            Intent intent  = new Intent(SignUpType.this, SignUpHost.class);
                            startActivity(intent);
                        }
                    }
                }
        );
    }
}
