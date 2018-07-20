package com.flipper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flipper.Database.Contact;
import com.flipper.R;

import java.util.List;


public class DataBaseAdapter extends RecyclerView.Adapter<DataBaseAdapter.MyViewHolder> {

    private List<Contact> moviesList;
    Context mcontext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,price,catid;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txt1);


        }
    }


    public DataBaseAdapter(Context mcontext, List<Contact> moviesList) {
        this.mcontext = mcontext;
        this.moviesList = moviesList;

    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rowdatabase, parent, false);
 
        return new MyViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Contact movie = moviesList.get(position);
        holder.title.setText(""+movie.getStatus());


    }
 
    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}