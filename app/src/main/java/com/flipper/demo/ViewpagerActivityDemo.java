package com.flipper.demo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.flipper.Database.Contact;
import com.flipper.Database.DatabaseHandler;
import com.flipper.R;
import com.flipper.StatusLisActivity;
import com.flipper.adapter.CustomPagerAdapter;
import com.flipper.adapter.DataBaseAdapter;
import com.flipper.model.DataModel;

import java.util.ArrayList;
import java.util.List;

public class ViewpagerActivityDemo extends Activity {
    protected GestureDetector gestureScanner;
    protected ViewFlipper vf;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    TextView tv;
    String[] name = { "1", "2", "3", "4" };
    int[] color = { Color.BLUE, Color.RED, Color.CYAN, Color.GREEN };
    int[] background = { Color.RED, Color.CYAN, Color.MAGENTA, Color.BLUE };

    int pos = 1;
    private Animation slideLeftIn;
    private Animation slideLeftOut;
    private Animation slideRightIn;
    private Animation slideRightOut;
    RelativeLayout ll;
    ImageView image;
    ImageView profilePic;
    CountDownTimer timer;
    CountDownTimer timer2;

    SeekBar seekBar;
    ProgressBar pb;
    int i  = 0;
    ViewPager viewpager;
    CustomPagerAdapterDemo myCustomPagerAdapter;
    RelativeLayout rl,left,right;
    DatabaseHandler db;
    ArrayList<Contact> contacts;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_main);
        db = new DatabaseHandler(this);

        idMapping();
        setonClick();
        setArray();



       // set();
    }

    private void idMapping() {
        viewpager = findViewById(R.id.viewpager);
        pb = findViewById(R.id.pb);
        rl = findViewById(R.id.rlmain);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        contacts = new ArrayList<>();
    }
    private void setonClick() {
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   Toast.makeText(ViewpagerActivity.this,"left",Toast.LENGTH_SHORT).show();
                int previuos = viewpager.getCurrentItem()-1;
                if(previuos>=0){
                    viewpager.setCurrentItem(previuos);
                    i=0;
                    rl.setBackgroundColor(Color.parseColor(contacts.get(previuos).getBgcolor()));


                }



            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ViewpagerActivity.this,"right",Toast.LENGTH_SHORT).show();

                int next = viewpager.getCurrentItem()+1;
                if(next<contacts.size()){
                    viewpager.setCurrentItem(next);
                    i=0;
                    rl.setBackgroundColor(Color.parseColor(contacts.get(next).getBgcolor()));

                }

            }
        });
    }
    private void setArray() {

        contacts = (ArrayList<Contact>) db.getAllContacts();

        myCustomPagerAdapter = new CustomPagerAdapterDemo(ViewpagerActivityDemo.this, contacts);
        viewpager.setAdapter(myCustomPagerAdapter);

        pos=0;
        viewpager.setCurrentItem(0);
        rl.setBackgroundColor(Color.parseColor(contacts.get(0).getBgcolor()));



        timer = new CountDownTimer(400, 400) {

            @Override
            public void onTick(long millisUntilFinished)
            {

            }
            @Override
            public void onFinish() {
                i = i+8;
                pb.setProgress(i);



                if(i>=100){

                    int next = viewpager.getCurrentItem()+1;
                    if(next<contacts.size()){
                        viewpager.setCurrentItem(next);
                        i=0;
                        rl.setBackgroundColor(Color.parseColor(contacts.get(next).getBgcolor()));

                    }

                    // pos++;

                }

                timer.start();

            }
        }.start();

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            public void onPageScrollStateChanged(int state) {
                i=0;
            }
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                i=0;
            }

            public void onPageSelected(int position) {
                Log.e("pos",""+position);
                i=0;
                pos = position;
                rl.setBackgroundColor(Color.parseColor(contacts.get(pos).getBgcolor()));

                // Check if this is the page you want.
            }
        });

    }



}
