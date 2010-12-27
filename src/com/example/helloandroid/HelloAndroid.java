package com.example.helloandroid;

import android.app.Activity;
import android.app.AlarmManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.Calendar;
import java.lang.Runnable;

public class HelloAndroid extends Activity
{
//	private Handler mHandler = new Handler();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

//		Button button = (Button) findViewById(R.id.reset);
//		button.setOnClickListener(mResetListener);
	}

	private OnClickListener mResetListener = new OnClickListener() {
		public void onClick(View v) {
			// set up an alarm
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(System.currentTimeMillis());
			cal.add(Calendar.SECOND, 60);

//			mHandler.postAtTime(mTriggerTimer, cal.getTimeInMillis());
		}
	};

	private Runnable mTriggerTimer = new Runnable() {
		public void run() {
			
		}
	};
}
