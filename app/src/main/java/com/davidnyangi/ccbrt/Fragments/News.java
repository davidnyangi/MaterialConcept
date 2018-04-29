package com.davidnyangi.ccbrt.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.davidnyangi.ccbrt.Adapters.NewsAdapter;
import com.davidnyangi.ccbrt.DetailNews;
import com.davidnyangi.ccbrt.Objects.NewsObj;
import com.davidnyangi.ccbrt.R;

import java.util.ArrayList;

public class News extends Fragment {

    String param;

    ArrayList<NewsObj> items;
    RecyclerView.LayoutManager lManager;
    RecyclerView recycler;
    NewsAdapter adapter;

    public News() {
    }
    public static News newInstance(String... params) {
        News fragment = new News();
        Bundle args = new Bundle();

        /* Obtener parametros en el fragmento*/
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

        View view = inflater.inflate(R.layout.fragment_news, container, false);

        RelativeLayout lastest = (RelativeLayout) view.findViewById(R.id.lastest_news);

        lastest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DetailNews.class);
                Bundle bundle = new Bundle();
                bundle.putString("TITLE", "Filling the Human Resources for Health Gap");
                bundle.putString("DATE", "Friday, 27th April 2016");

                bundle.putInt("ITEM", 6);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        recycler = (RecyclerView) view.findViewById(R.id.recycler_news);
        recycler.setHasFixedSize(true);
        lManager = new LinearLayoutManager(getActivity());
        recycler.setNestedScrollingEnabled(false);
        recycler.setLayoutManager(lManager);

        items = new ArrayList<>();

        // String title, int image, long date
        items.add(new NewsObj("World Malaria Day: Malaria and Disability", R.drawable.bb, "Wednesday, April 25 2018"));
        items.add(new NewsObj("Investing in HRH: Meet Doris, Evelyn & Elizabeth", R.drawable.cc, "Saturday, April 21 2018"));
        items.add(new NewsObj("What’s New at CCBRT: Digital X-Rays", R.drawable.dd, "Wednesday, April 18 2018"));
        items.add(new NewsObj("CCBRT Academy: Coming Soon", R.drawable.ee, "Friday, April 13 2018"));
        items.add(new NewsObj("Dr James & Dr Peter: HR for Fistula Care", R.drawable.ff, "Wednesday, April 11 2018"));
        items.add(new NewsObj("World Health Workers Week: Meet Ladness", R.drawable.gg, "Friday, April 06 2018"));

        adapter = new NewsAdapter(items, getContext());
        recycler.setAdapter(adapter);

        return view;
    }

}
