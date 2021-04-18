package com.IDP.Group1.acr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashScreen extends AppCompatActivity {
	private ProgressBar pb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_splash_screen);

		pb = (ProgressBar) findViewById(R.id.progressBarID);

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				doWork();
				startApp();
			}
		});
		thread.start();

		Log.d("rifat", "debugging");

	}

	private void startApp() {
		Intent intent;

		if (FirebaseAuth.getInstance().getCurrentUser() != null) {
			intent = new Intent(this, Home.class);
			startActivity(intent);
		}
		else {
			intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
		finish();
	}

	private void doWork() {
		for (int p = 20; p <= 100; p += 20){
			try {
				Thread.sleep(500);
				pb.setProgress(p);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
