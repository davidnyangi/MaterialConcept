package com.davidnyangi.ccbrt.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.davidnyangi.ccbrt.Adapters.ActivityDiaryAdapter;
import com.davidnyangi.ccbrt.Objects.ActivityDiary;
import com.davidnyangi.ccbrt.R;

import java.util.ArrayList;


public class Contacts extends Fragment {

    String param;


    public Contacts() {
    }
    public static Contacts newInstance(String... params) {
        Contacts fragment = new Contacts();
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

        View view = inflater.inflate(R.layout.fragment_contacts, container, false);



        return view;
    }
}
