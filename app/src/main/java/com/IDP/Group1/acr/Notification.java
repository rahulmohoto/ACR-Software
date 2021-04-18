package com.IDP.Group1.acr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Notification extends AppCompatActivity {

	ListView listView;
	User user;
	List<String> stringList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notifcation);

		listView = findViewById(R.id.listViewID);
		user = new User();
		stringList = user.getNotification();

		String []strings = new String[stringList.size()];
		stringList.toArray(strings);

		ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.layout_text_only, R.id.onlyTextID, strings);
		listView.setAdapter(adapter);
	}
}
