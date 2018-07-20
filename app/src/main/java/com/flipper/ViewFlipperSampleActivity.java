package com.flipper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;


import com.flipper.model.DataModel;

import java.util.ArrayList;

public class ViewFlipperSampleActivity extends Activity {
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private ViewFlipper mViewFlipper;
	private Context mContext;
	private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());
	CountDownTimer timer;
	LinearLayout llmain;
	int i = 0;
	ImageView playprevious,playnext;
	ArrayList<DataModel> dataModels;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_flipper);
		mContext = this;

        idMapping();
        setonClick();
        setArrayList();

        startFlipping();

	}

	private void startFlipping() {

		timer = new CountDownTimer(4000, 4000) {

			@Override
			public void onTick(long millisUntilFinished)
			{

			}
			@Override
			public void onFinish() {



				i++;

				setFlipperImage(i);

				timer.start();

			}
		}.start();
	}


	private void stopFlipping() {

		timer.cancel();

		mViewFlipper.stopFlipping();
	}

	private void idMapping() {
		mViewFlipper = (ViewFlipper) this.findViewById(R.id.view_flipper);
		mViewFlipper.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(final View view, final MotionEvent event) {
				detector.onTouchEvent(event);
				return true;
			}
		});
		llmain= findViewById(R.id.llmain);

		playprevious =findViewById(R.id.playprevious);
		playnext =findViewById(R.id.playnext);

	}
	private void setonClick() {

		playprevious.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
			// stop the flipping of views

				stopFlipping();

				mViewFlipper.showPrevious();
				i = i-1;
				setFlipperImage(i);

			}
		});

		playnext.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				stopFlipping();

				mViewFlipper.showNext();
				i = i+1;
				setFlipperImage(i);

			}
		});

	}

	private void setArrayList() {

		dataModels= new ArrayList<>();


		dataModels.add(new DataModel("Hello 1", "b","ss"));
		dataModels.add(new DataModel("2 test viewflippeer", "red","a"));

		dataModels.add(new DataModel("3 test demo", "g","aa"));
		dataModels.add(new DataModel("4 gfgdf", "o","a"));

		setFirst();


	}

	private void setFirst() {

		TextView tv = findViewById(R.id.TextView01);
		if(tv.getParent()!=null)
			((ViewGroup)tv.getParent()).removeView(tv); // <- fix


		tv.setText(""+dataModels.get(i).getStatus());
		String color = dataModels.get(i).getBgcolor();
		if(color.equalsIgnoreCase("b")){
			llmain.setBackgroundColor(getResources().getColor(R.color.black));

		}
		mViewFlipper.addView(tv);


		mViewFlipper.setDisplayedChild(1);
	}

	class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			try {

				stopFlipping();


                // right to left swipe
				if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_in_left));
					mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_out_left));
					mViewFlipper.showNext();

					i = i+1;
					setFlipperImage(i);

					return true;
				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_in_right));
					mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,R.anim.slide_out_right));
					mViewFlipper.showPrevious();
					i = i-1;
					setFlipperImage(i);
					return true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return false;
		}
	}

	private void setFlipperImage(int res) {
		Log.e("iiii", res+"");
		if(res<dataModels.size()){

			TextView tv = findViewById(R.id.TextView01);
			if(tv.getParent()!=null)
				((ViewGroup)tv.getParent()).removeView(tv); // <- fix




			tv.setText(""+dataModels.get(i).getStatus());
			String color = dataModels.get(i).getBgcolor();
			if(color.equalsIgnoreCase("b")){
				llmain.setBackgroundColor(getResources().getColor(R.color.black));

			}
			else if(color.equalsIgnoreCase("red")){
				llmain.setBackgroundColor(getResources().getColor(R.color.red));

			}
			else if(color.equalsIgnoreCase("g")){
				llmain.setBackgroundColor(getResources().getColor(R.color.lightGreen));

			}
			else if(color.equalsIgnoreCase("o")){
				llmain.setBackgroundColor(getResources().getColor(R.color.orange));

			}
			mViewFlipper.addView(tv);
		}

		else {

			Toast.makeText(ViewFlipperSampleActivity.this,"done",Toast.LENGTH_SHORT).show();
			stopFlipping();
			finish();
		}




	}
}