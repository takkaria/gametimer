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

		mBackground = (TransitionDrawable) getResources().getDrawable(R.drawable.reset);
//		mBackground.setCrossFadeEnabled(true);
	}

	private OnClickListener mResetListener = new OnClickListener() {
		public void onClick(View v) {
			Button button = (Button) findViewById(R.id.reset);			
			button.setText("Reset");

			mHandler.removeCallbacks(mTriggerTimer);
			mHandler.postDelayed(mTriggerTimer, 5000);
		}
	};

	private Runnable mTriggerTimer = new Runnable() {
		public void run() {
			Button button = (Button) findViewById(R.id.reset);			
			mBackground.startTransition(500);
			button.setText("Finished");
		}
	};
}
