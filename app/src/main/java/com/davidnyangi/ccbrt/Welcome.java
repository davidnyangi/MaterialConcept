package com.davidnyangi.ccbrt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Welcome extends AppCompatActivity {

    @Bind(R.id.link_signup) TextView linkSignup;
    @Bind(R.id.link_reset_password)  TextView linkResetPassword;
    @Bind(R.id.btn_login)  AppCompatButton btnLogin;
    Spinner mLanguages;
    ArrayList<String>  sLanguagess = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        linkSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this, Login.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        linkResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this, ResetPassword.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this, Home.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
        sLanguagess.add("Kiswahili");
        sLanguagess.add("English");String[] sLanguages = {"Kiswahili","English"};
        mLanguages = (Spinner)findViewById(R.id.spinnerLang);
//        ArrayAdapter<String> adapter = ArrayAdapter.createFromResource(this,
//                R.array.sLanguages,);
        ArrayAdapter<CharSequence> langAdapter = new ArrayAdapter<CharSequence>(Welcome.this,  R.layout.simple_spinner_item, sLanguages );
        langAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        mLanguages.setAdapter(langAdapter);
    }
}
