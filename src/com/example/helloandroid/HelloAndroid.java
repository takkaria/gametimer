package com.example.helloandroid;

import android.app.Activity;
import android.app.AlarmManager;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.Calendar;
import java.lang.Runnable;

public class HelloAndroid extends Activity
{
	private Handler mHandler = new Handler();
	private TransitionDrawable mBackground;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button button = (Button) findViewById(R.id.reset);
		button.setOnClickListener(mResetListener);

		button.setBackgroundResource(R.drawable.reset);
		mBackground = (TransitionDrawable) button.getBackground();
		mBackground.setCrossFadeEnabled(true);
	}

	private OnClickListener mResetListener = new OnClickListener() {
		public void onClick(View v) {
			Button button = (Button) findViewById(R.id.reset);			
			button.setText("");

			mHandler.removeCallbacks(mTimeUp);
			mHandler.removeCallbacks(mGoGreen);
			mHandler.removeCallbacks(mGoRed);

			mBackground.startTransition(10000);
			mHandler.postDelayed(mTimeUp, 10000);
		}
	};

	private Runnable mTimeUp = new Runnable() {
		public void run() {
			Button button = (Button) findViewById(R.id.reset);			
			button.setText("Restart");

			mGoGreen.run();
		}
	};

	private Runnable mGoGreen = new Runnable() {
		public void run() {
			mBackground.reverseTransition(1000);
			mHandler.postDelayed(mGoRed, 2000);
		}
	};

	private Runnable mGoRed = new Runnable() {
		public void run() {
			mBackground.reverseTransition(1000);
			mHandler.postDelayed(mGoGreen, 2000);
		}
	};

}
