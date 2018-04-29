package com.davidnyangi.ccbrt;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailNews extends AppCompatActivity {

    @Bind(R.id.main_picture) ImageView main_picture;
    @Bind(R.id.date) TextView date;
    @Bind(R.id.title) TextView title;


    int[] images = {
            R.drawable.aa,
            R.drawable.bb,
            R.drawable.cc,
            R.drawable.dd,
            R.drawable.ee,
            R.drawable.ff,
            R.drawable.gg

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        final Bundle bundle = this.getIntent().getExtras();

        date.setText(bundle.getString("DATE"));
        title.setText(bundle.getString("TITLE"));

        Glide.
            with(this)
            .load(images[bundle.getInt("ITEM")])
            .centerCrop()
            //.placeholder(R.drawable.loading_spinner)
            .crossFade()
            .into(main_picture);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                share.putExtra(Intent.EXTRA_TEXT, bundle.getString("TITLE"));
                share.setType("text/plain");
                startActivity(Intent.createChooser(share, "Compartir por..."));
            }
        });
    }
}
