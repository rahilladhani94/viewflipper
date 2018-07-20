package com.flipper;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;


import com.flipper.model.DataModel;

import java.util.ArrayList;

public class MainActivity extends Activity implements OnGestureListener {
    protected GestureDetector gestureScanner;
    protected ViewFlipper vf;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    TextView tv;
    String[] name = { "1", "2", "3", "4" };
    int[] color = { Color.BLUE, Color.RED, Color.CYAN, Color.GREEN };
    int[] background = { Color.RED, Color.CYAN, Color.MAGENTA, Color.BLUE };

    int pos = 0;
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
    ArrayList<DataModel> dataModels;
    RelativeLayout previous,next;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        gestureScanner = new GestureDetector(this);
        setContentView(R.layout.activity_flipper);
        idMapping();
        setonClick();
        setArray();



       // set();
    }

    private void idMapping() {
        ll = findViewById(R.id.ll);
        tv = findViewById(R.id.txtStatus);
        previous = findViewById(R.id.previous);
        next = findViewById(R.id.next);

        slideLeftIn = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        slideLeftOut = AnimationUtils
                .loadAnimation(this, R.anim.slide_out_left);
        slideRightIn = AnimationUtils
                .loadAnimation(this, R.anim.slide_in_right);
        slideRightOut = AnimationUtils.loadAnimation(this,
                R.anim.slide_out_right);

        seekBar =findViewById(R.id.seekBar);
        pb=findViewById(R.id.pb);
        seekBar.setMax(100);
        pb.setMax(100);
    }
    private void setonClick() {
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll.setAnimation(slideRightIn);
                ll.setAnimation(slideRightOut);
                showPrevious();

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll.setAnimation(slideLeftIn);
                ll.setAnimation(slideLeftOut);
                showNext();
            }
        });
    }
    private void setArray() {
        dataModels= new ArrayList<>();

        dataModels.add(new DataModel("Hello 1", "#85C226","#000000"));
        dataModels.add(new DataModel("2 test viewflippeer", "#ff0000","#ffffff"));

        dataModels.add(new DataModel("3 test demo", "#df4914","#000000"));
        dataModels.add(new DataModel("4 gfgdf", "#9965a1ff","#373151"));

        setFirst();
        stattflipping();
    }

    private void setFirst() {
        setText(pos);
    }

    private void showPrevious(){
        if(pos>0){
            ll.setAnimation(slideRightIn);
            ll.setAnimation(slideRightOut);
            pos = pos -1;
            setText(pos);
            i =0;
        }

    }
    private void showNext(){
        ll.setAnimation(slideLeftIn);
        ll.setAnimation(slideLeftOut);
        pos = pos +1;
        setText(pos);
        i =0;
    }
    private void  stattflipping(){

        timer = new CountDownTimer(300, 300) {

            @Override
            public void onTick(long millisUntilFinished)
            {

            }
            @Override
            public void onFinish() {
                i = i+3;
                seekBar.setProgress(i);
                pb.setProgress(i);
                if(i>=100){
                    try{
                        ll.setAnimation(slideLeftIn);
                        ll.setAnimation(slideLeftOut);

                        pos++;
                        if (pos == 0) {
                            setText(pos);
                        } else {

                            setText(pos);
                        }
                        i=0;
                    }catch(Exception e){
                        Log.e("Error", "Error: " + e.toString());
                    }


                }

                timer.start();

            }
        }.start();
    }


    @Override
    public boolean onTouchEvent(MotionEvent me) {
        return gestureScanner.onTouchEvent(me);
    }

    public boolean onDown(MotionEvent e) {
        return true;
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        try {
            if (e1.getX() > e2.getX()
                    && Math.abs(e1.getX() - e2.getX()) > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                Toast.makeText(this.getApplicationContext(), "Left",
                        Toast.LENGTH_SHORT).show();
                // vf.showPrevious();
                ll.setAnimation(slideLeftIn);
                ll.setAnimation(slideLeftOut);
                showNext();

//                pos++;
//                if (pos == 0) {
//                    setText(pos);
//                } else {
//
//                    setText(pos);
//                }

            } else if (e1.getX() < e2.getX()
                    && e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                Toast.makeText(this.getApplicationContext(), "Right",
                        Toast.LENGTH_SHORT).show();
                // vf.showNext();
                ll.setAnimation(slideRightIn);
                ll.setAnimation(slideRightOut);
                showPrevious();
//                if (pos == 0) {
//                    setText(pos);
//                } else {
//
//                    setText(pos);
//                }
            }
        } catch (Exception e) {
            // nothing
        }
        return true;

    }

    public void onLongPress(MotionEvent e) {
    }

    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        return true;
    }

    public void onShowPress(MotionEvent e) {
    }

    public boolean onSingleTapUp(MotionEvent e) {

        return true;
    }

    private void setText(int position) {


        if(position<dataModels.size()){
        tv.setText(dataModels.get(position).getStatus());
        tv.setTextColor(Color.parseColor(dataModels.get(position).getTextcolor()));
        ll.setBackgroundColor(Color.parseColor(dataModels.get(position).getBgcolor()));
        }
    }


}
