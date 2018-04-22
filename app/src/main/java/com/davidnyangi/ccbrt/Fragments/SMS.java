package com.davidnyangi.ccbrt.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.davidnyangi.ccbrt.Adapters.PersonAdapter;
import com.davidnyangi.ccbrt.EndPoints;
import com.davidnyangi.ccbrt.MyVolley;
import com.davidnyangi.ccbrt.Objects.Person;
import com.davidnyangi.ccbrt.R;
import com.davidnyangi.ccbrt.Welcome;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SMS extends Fragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    String param;
    private Button buttonSendPush;
    private RadioGroup radioGroup;
    private Spinner spinner;
    private ProgressDialog progressDialog;

    private EditText editTextTitle, editTextMessage;
    EditText editTextImage;

    private boolean isSendAllChecked;
    private List<String> devices;
    public SMS() {
    }
    public static SMS newInstance(String... params) {
        SMS fragment = new SMS();
        Bundle args = new Bundle();

        /* Obtener parametros en e fragmentos*/
        args.putString("KEY", params[0]);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            param = getArguments().getString("KEY");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sms, container, false);

        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        spinner = (Spinner) view.findViewById(R.id.spinnerDevices);
        buttonSendPush = (Button) view.findViewById(R.id.buttonSendPush);

        editTextTitle = (EditText) view.findViewById(R.id.editTextTitle);
        editTextMessage = (EditText) view.findViewById(R.id.editTextMessage);
        editTextImage = (EditText) view.findViewById(R.id.editTextImageUrl);

        devices = new ArrayList<>();

        radioGroup.setOnCheckedChangeListener(this);
        buttonSendPush.setOnClickListener(this);

        loadRegisteredDevices();
        return view;
    }
    //method to load all the devices from database
    private void loadRegisteredDevices() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Fetching Users...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, EndPoints.URL_FETCH_DEVICES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dismissProgressDialog();
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                JSONArray jsonDevices = obj.getJSONArray("devices");

                                for (int i = 0; i < jsonDevices.length(); i++) {
                                    JSONObject d = jsonDevices.getJSONObject(i);
                                    devices.add(d.getString("email"));
                                }
                                ArrayAdapter<String> langAdapter = new ArrayAdapter<String>(getActivity(),  R.layout.simple_spinner_item, devices );
                                langAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                                spinner.setAdapter(langAdapter);
//                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
//                                        getActivity(),
//                                        android.R.layout.simple_spinner_dropdown_item,
//                                        devices);
//
//                                spinner.setAdapter(arrayAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

        };
        MyVolley.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    //this method will send the push
    //from here we will call sendMultiple() or sendSingle() push method
    //depending on the selection
    private void sendPush() {
        if(isSendAllChecked){
            sendMultiplePush();
        }else{
            sendSinglePush();
        }
    }
    private void sendMultiplePush() {
        final String title = editTextTitle.getText().toString();
        final String message = editTextMessage.getText().toString();
        final String image = editTextImage.getText().toString();

        progressDialog.setMessage("Sending Message...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoints.URL_SEND_MULTIPLE_PUSH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("title", title);
                params.put("message", message);

                if (!TextUtils.isEmpty(image))
                    params.put("image", image);
                return params;
            }
        };

        MyVolley.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private void sendSinglePush() {
        final String title = editTextTitle.getText().toString();
        final String message = editTextMessage.getText().toString();
        final String image = editTextImage.getText().toString();
        final String email = spinner.getSelectedItem().toString();

        progressDialog.setMessage("Sending Message...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoints.URL_SEND_SINGLE_PUSH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        Toast.makeText(getActivity(), "Successfully Sent", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("title", title);
                params.put("message", message);

                if (!TextUtils.isEmpty(image))
                    params.put("image", image);

                params.put("email", email);
                return params;
            }
        };

        MyVolley.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radioButtonSendAll:
                isSendAllChecked = true;
                spinner.setEnabled(false);
                break;

            case R.id.radioButtonSendOne:
                isSendAllChecked = false;
                spinner.setEnabled(true);
                break;

        }
    }

    @Override
    public void onClick(View view) {
        //calling the method send push on button click
        sendPush();
    }
    private void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        dismissProgressDialog();
        super.onDestroy();
    }
}
