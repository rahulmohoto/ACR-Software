package com.IDP.Group1.acr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;

public class Accounts extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accounts);

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		int width = (int)(0.5 * dm.widthPixels);
		int height = (int)(0.5 * dm.heightPixels);

		getWindow().setLayout(width, height);
	}
}
