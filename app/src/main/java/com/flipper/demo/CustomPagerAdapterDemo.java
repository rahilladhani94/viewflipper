package com.flipper.demo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flipper.Database.Contact;
import com.flipper.R;
import com.flipper.model.DataModel;

import java.util.ArrayList;

public class CustomPagerAdapterDemo extends PagerAdapter{
    Context context;

    LayoutInflater layoutInflater;
    ArrayList<Contact> dataModels;


    public CustomPagerAdapterDemo(Context context, ArrayList<Contact> dataModels) {
        this.context = context;
        this.dataModels = dataModels;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataModels.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.viewpager_row, container, false);

        TextView txt = (TextView) itemView.findViewById(R.id.txtStatus);
        LinearLayout ll = itemView.findViewById(R.id.ll);

        txt.setText(dataModels.get(position).getStatus());

        int size = Integer.parseInt(dataModels.get(position).getTextsize());
        txt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);// for Italic

        if(dataModels.get(position).getFontstyle().equalsIgnoreCase("b")){
            txt.setTypeface(null, Typeface.BOLD);

        }
        else if(dataModels.get(position).getFontstyle().equalsIgnoreCase("i")){
            txt.setTypeface(null, Typeface.ITALIC);
        }
        ll.setBackgroundColor(Color.parseColor(dataModels.get(position).getBgcolor()));




        container.addView(itemView);

        //listening to image click


        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

}