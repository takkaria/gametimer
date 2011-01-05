package org.takkaria.gametimer;

import android.app.Activity;
import android.graphics.drawable.TransitionDrawable;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.lang.Runnable;

public class GameTimer extends Activity
{
	private Handler mHandler = new Handler();
	private Button mButton;
	private CountDownTimer mTimer;
	private Integer timeLimit = 60000;
	private Integer warningLimit = 10000;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mButton = (Button) findViewById(R.id.reset);
		mButton.setOnClickListener(mResetListener);
		
		mTimer = new CountDownTimer(timeLimit, 200) {
			public void onTick(long untilFinished) {
				mButton.setText(Integer.toString((int) (untilFinished + 1000) / 1000));
				
				if (untilFinished / 1000 == (int) warningLimit / 1000)
				{
						mTimeRunningOut.run();
				}
			}
			
			public void onFinish() {
				mButton.setText("Time up");
				mFlashScreen.run();
			}
		};
	}
	
	/*
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	}
	*/

	private OnClickListener mResetListener = new OnClickListener() {
		public void onClick(View v) {		
			mButton.setText("");
			
			mHandler.removeCallbacks(mFlashScreen);

			mButton.setBackgroundResource(R.drawable.start);
			((TransitionDrawable) mButton.getBackground()).startTransition(1000);
			mTimer.cancel();
			mTimer.start();
		}
	};

	private Runnable mTimeRunningOut = new Runnable() {
		public void run() {
			mButton.setBackgroundResource(R.drawable.end);
			((TransitionDrawable) mButton.getBackground()).startTransition(warningLimit);
		}
	};
	

	private Runnable mFlashScreen = new Runnable() {
		public void run() {
			((TransitionDrawable) mButton.getBackground()).reverseTransition(500);
			mHandler.postDelayed(mFlashScreen, 1000);
		}
	};
}
