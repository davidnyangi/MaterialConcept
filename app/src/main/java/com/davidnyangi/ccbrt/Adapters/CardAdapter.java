package com.davidnyangi.ccbrt.Adapters;

/**
 * Created by nyangi.admin on 4/25/2018.
 */
import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.davidnyangi.ccbrt.CustomVolleyRequest;
import com.davidnyangi.ccbrt.R;
import com.davidnyangi.ccbrt.Services;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    //Imageloader to load image
    private ImageLoader imageLoader;
    private Context context;

    //List to store all Serviceses
    List<Services> Serviceses;

    //Constructor of this class
    public CardAdapter(List<Services> Serviceses, Context context){
        super();
        //Getting all Serviceses
        this.Serviceses = Serviceses;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.services_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //Getting the particular item from the list
        Services Services =  Serviceses.get(position);

        //Loading image from url
        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(Services.getImageUrl(), ImageLoader.getImageListener(holder.imageView, R.mipmap.logo, android.R.drawable.ic_dialog_alert));

        //Showing data on the views
        holder.imageView.setImageUrl(Services.getImageUrl(), imageLoader);
        holder.textViewName.setText(Services.getName());
        holder.textViewName.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f);
        holder.textViewPublisher.setText(Services.getPublisher());

    }

    @Override
    public int getItemCount() {
        return Serviceses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        //Views
        public NetworkImageView imageView;
        public TextView textViewName;
        public TextView textViewPublisher;

        //Initializing Views
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (NetworkImageView) itemView.findViewById(R.id.imageViewHero);
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewPublisher = (TextView) itemView.findViewById(R.id.textViewPublisher);
        }
    }
}
