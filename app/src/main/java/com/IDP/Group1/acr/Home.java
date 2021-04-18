package com.IDP.Group1.acr;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Objects;

public class Home extends actionBar {

	private AppBarConfiguration mAppBarConfiguration;
	User user;
	TextView userName, userEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		Toolbar toolbar = findViewById(R.id.toolbar);
		toolbar.setTitle(R.string.app_name);
		toolbar.setCollapseIcon(R.drawable.app_logo);
		toolbar.setNavigationIcon(R.drawable.app_logo);
		setSupportActionBar(toolbar);
//		toolbar.setLogo(R.drawable.app_logo);
		Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_name);

		user = new User();
//		user.writeData(user);
//		Toast.makeText(this, )

		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		NavigationView navigationView = findViewById(R.id.nav_view);
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		mAppBarConfiguration = new AppBarConfiguration.Builder(
				R.id.nav_Dashboard, R.id.nav_Clean, R.id.nav_Shedule,
				R.id.nav_FloorMapping, R.id.nav_KidMode, R.id.nav_analytics, R.id.nav_objectDetect,
				R.id.nav_feedback, R.id.nav_Setting, R.id.nav_about)
				.setDrawerLayout(drawer)
				.build();
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
		NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
		NavigationUI.setupWithNavController(navigationView, navController);
		View view = navigationView.getHeaderView(0);

		userName = view.findViewById(R.id.userNameID);
		userEmail = view.findViewById(R.id.userEmailID);

		userName.setText(user.getName());
		userEmail.setText(user.getEmail());


		navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
			@Override
			public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

			}
		});

//		navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//			@Override
//			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//				if (item.getItemId() == R.id.shareID){
//					ApplicationInfo api = getApplicationContext().getApplicationInfo();
//					String apkPath = api.sourceDir;
//
//					Intent intent = new Intent(Intent.ACTION_SEND);
//					intent.setType("application/vnd.android.package-archive");
//					intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkPath)));
//					startActivity(Intent.createChooser(intent, "Share app using"));
//				}
//				else if (item.getItemId() == R.id.feedbackID){
//					Intent intent = new Intent(Home.class, feedback.class);
//					startActivity(intent);
//				}
//				else if (item.getItemId() == R.id.aboutID){
//					Intent intent = new Intent(this, about.class);
//					startActivity(intent);
//				}
//				else if(item.getItemId() == R.id.exitID) {
//					finish();
//				}
//				else if (item.getItemId() == R.id.logOutID) {
//					FirebaseAuth.getInstance().signOut();
//					finish();
//
//					Toast.makeText(actionBar.this, "ok sign out", Toast.LENGTH_SHORT).show();
//					Intent intent = new Intent(this, MainActivity.class);
//					startActivity(intent);
//				}
//				return true;
//			}
//		});

//		getSupportActionBar().setHomeButtonEnabled(true);
//		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//		getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_logo_small);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}

	@Override
	public boolean onSupportNavigateUp() {
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
		return NavigationUI.navigateUp(navController, mAppBarConfiguration)
				|| super.onSupportNavigateUp();
	}
}
