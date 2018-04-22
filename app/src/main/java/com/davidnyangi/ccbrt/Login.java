package com.davidnyangi.ccbrt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.Bind;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
public class Login extends AppCompatActivity {

    @Bind(R.id.mobile_code) TextInputLayout mPhoneCode;
    @Bind(R.id.link_login) TextView linkLogin;
    @Bind(R.id.btn_login) Button btnLogin;
    private Button buttonDisplayToken;
    private TextView textViewToken;
    private ProgressDialog progressDialog;
    private static final String URL_REGISTER_DEVICE = "https://davidnyangiprojects.com/Projects/Android/CCBRT/RegisterDevice.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
      //  textViewToken = (TextView) findViewById(R.id.textViewToken);
//        buttonDisplayToken = (Button) findViewById(R.id.buttonDisplayToken);

        //adding listener to view
//        buttonDisplayToken.setOnClickListener(new View.OnClickListener() {
//              @Override
//              public void onClick(View v) {
//                  if (v == buttonDisplayToken) {
//                      //getting token from shared preferences
//                      FirebaseMessaging.getInstance().subscribeToTopic("Hithere");
//                      String token = FirebaseInstanceId.getInstance().getToken();
//
//                      //if token is not null
//                      if (token != null) {
//                          //displaying the token
//                          textViewToken.setText(token);
//                      } else {
//                          //if token is null that means something wrong
//                          textViewToken.setText("Token not generated");
//                      }
//                  }
//              }
//          });
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this,Welcome.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendTokenToServer();
                Intent intent = new Intent(Login.this, Home.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

    }
    //storing token to mysql server
    private void sendTokenToServer() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        progressDialog.setCancelable(true);
        FirebaseMessaging.getInstance().subscribeToTopic("Hithere");
        final String token = FirebaseInstanceId.getInstance().getToken();
        final String email = mPhoneCode.getEditText().getText().toString();

        if (token == null) {
            progressDialog.dismiss();
            Toast.makeText(this, "Token not generated", Toast.LENGTH_LONG).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER_DEVICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dismissProgressDialog();
                        try {
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(Login.this, obj.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("token", token);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        dismissProgressDialog();
        super.onDestroy();
    }
}
