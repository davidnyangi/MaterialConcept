package com.davidnyangi.ccbrt.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davidnyangi.ccbrt.Adapters.PersonAdapter;
import com.davidnyangi.ccbrt.Objects.Person;
import com.davidnyangi.ccbrt.R;

import java.util.ArrayList;


public class People extends Fragment {

    String param;

    ArrayList<Person> items;
    RecyclerView recycler;
    LinearLayoutManager lManager;
    PersonAdapter adapter;

    public People() {
    }
    public static People newInstance(String... params) {
        People fragment = new People();
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
        View view = inflater.inflate(R.layout.fragment_people, container, false);
        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        lManager = new LinearLayoutManager(getActivity());
        recycler.setNestedScrollingEnabled(false);
        recycler.setLayoutManager(lManager);
        items = new ArrayList<>();

        items.add(new Person("Edwin Kashangaki", "Software Developer", "david.nyangi@ccbrt.org", "https://davidnyangiprojects.com/Projects/Android/CCBRT/Images/male.png","https://davidnyangiprojects.com/Projects/Android/CCBRT/Images/male.png"));
        items.add(new Person("Sarah Sengerema", "Software Developer", "david.nyangi@ccbrt.org", "https://davidnyangiprojects.com/Projects/Android/CCBRT/Images/female.png",  "https://davidnyangiprojects.com/Projects/Android/CCBRT/Images/female.png"));
        items.add(new Person("Mwema Semkudi", "Software Developer", "david.nyangi@ccbrt.org", "https://davidnyangiprojects.com/Projects/Android/CCBRT/Images/female.png",  "https://davidnyangiprojects.com/Projects/Android/CCBRT/Images/female.png"));
        items.add(new Person("Mangalu Lastname", "Software Developer", "david.nyangi@ccbrt.org", "https://davidnyangiprojects.com/Projects/Android/CCBRT/Images/male.png",  "https://davidnyangiprojects.com/Projects/Android/CCBRT/Images/male.png"));
        items.add(new Person("David Nyangi", "Software Developer", "david.nyangi@ccbrt.org", "https://davidnyangiprojects.com/Projects/Android/CCBRT/Images/male.png",  "https://davidnyangiprojects.com/Projects/Android/CCBRT/Images/male.png"));
        items.add(new Person("Edwin Kashangaki", "Software Developer", "david.nyangi@ccbrt.org", "https://davidnyangiprojects.com/Projects/Android/CCBRT/Images/male.png","https://davidnyangiprojects.com/Projects/Android/CCBRT/Images/male.png"));
        items.add(new Person("Sarah Sengerema", "Software Developer", "david.nyangi@ccbrt.org", "https://davidnyangiprojects.com/Projects/Android/CCBRT/Images/female.png",  "https://davidnyangiprojects.com/Projects/Android/CCBRT/Images/female.png"));
        items.add(new Person("Mwema Semkudi", "Software Developer", "david.nyangi@ccbrt.org", "https://davidnyangiprojects.com/Projects/Android/CCBRT/Images/female.png",  "https://davidnyangiprojects.com/Projects/Android/CCBRT/Images/female.png"));
        items.add(new Person("Mangalu Lastname", "Software Developer", "david.nyangi@ccbrt.org", "https://davidnyangiprojects.com/Projects/Android/CCBRT/Images/male.png",  "https://davidnyangiprojects.com/Projects/Android/CCBRT/Images/male.png"));
        items.add(new Person("David Nyangi", "Software Developer", "david.nyangi@ccbrt.org", "https://davidnyangiprojects.com/Projects/Android/CCBRT/Images/male.png",  "https://davidnyangiprojects.com/Projects/Android/CCBRT/Images/male.png"));

        adapter = new PersonAdapter(items, getContext());
        recycler.setAdapter(adapter);

        return view;
    }
}
