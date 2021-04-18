package com.IDP.Group1.acr;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;

import static java.lang.Thread.sleep;


public class Dashboard extends Fragment {

	private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 1;
	private TextToSpeech textToSpeech;
	private SpeechRecognizer speechRecognizer;
	private GridView grid;
	private User user;
	ImageView cleanImage;
	pl.droidsonroids.gif.GifImageView cleanGif;
	GridView gridView;
	boolean isRecording;
	ImageView alert,sleep,notification,mic,settings, battery;
	TextView batteryText;
	boolean isMicOn;
	int[] images = {R.drawable.clean, R.drawable.schedule, R.drawable.floormapping, R.drawable.controller, R.drawable.analytics, R.drawable.objectdetect};
	String[] titles = {"Clean", "Schedule", "Floor Mapping", "Manual Control", "Analytics", "Object Detection"};
	public Dashboard() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View V = inflater.inflate(R.layout.fragment_dashboard, container, false);

		user = new User();
		final GifImageView cat = V.findViewById(R.id.cat);
		alert =V.findViewById(R.id.alertID);
		sleep =V.findViewById(R.id.sleepID);
		cleanImage = V.findViewById(R.id.sweep_img);
		cleanGif = V.findViewById(R.id.sweep_gif);
		notification =V.findViewById(R.id.notificationID);
		mic =V.findViewById(R.id.micID);
		gridView = V.findViewById(R.id.dashboardGridViewID);
		isMicOn = false;
		isRecording = false;

		custAdapter adapter = new custAdapter(getContext(), images, titles);
		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				FragmentManager fragmentManager = getFragmentManager();
				if (i == 0) {
					Clean clean = new Clean();
					fragmentManager.beginTransaction()
								.replace(R.id.nav_host_fragment, clean).commit();
				}
				else if (i == 1) {
					Shedule shedule = new Shedule();
					fragmentManager.beginTransaction()
							.replace(R.id.nav_host_fragment, shedule).commit();
				}
				else if (i == 2) {
					FloorMapping floorMapping = new FloorMapping();
					fragmentManager.beginTransaction()
							.replace(R.id.nav_host_fragment, floorMapping).commit();
				}
				else if (i == 3) {
					KidMode kidMode = new KidMode();
					fragmentManager.beginTransaction()
							.replace(R.id.nav_host_fragment, kidMode).commit();
				}
				else if (i == 4) {
					Analytics analytics = new Analytics();
					fragmentManager.beginTransaction()
							.replace(R.id.nav_host_fragment, analytics).commit();
				}
				else if (i == 5) {
					ObjectDetect objectDetect = new ObjectDetect();
					fragmentManager.beginTransaction()
							.replace(R.id.nav_host_fragment, objectDetect).commit();
				}
			}
		});
		cleanImage.setOnClickListener(new View.OnClickListener() {
			@SuppressLint("ShowToast")
			@Override
			public void onClick(View view) {
				cleanImage.setVisibility(View.GONE);
				cleanGif.setVisibility(View.VISIBLE);
				Toast.makeText(getContext(), "image", Toast.LENGTH_SHORT).show();
			}
		});

		cleanGif.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(getContext(), "gif", Toast.LENGTH_SHORT).show();
				cleanImage.setVisibility(View.VISIBLE);
				cleanGif.setVisibility(View.GONE);
			}
		});

		alert.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				user.isRinging = !user.isRinging;

				if (user.isRinging) {
					alert.setImageResource(R.drawable.alert_on);
					Toast.makeText(getContext(), "Ringing", Toast.LENGTH_SHORT).show();
				}
				else {
					alert.setImageResource(R.drawable.alert_off);
				}
			}
		});

		sleep.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				user.isSleep = !user.isSleep;

				if (user.isSleep) {
					sleep.setImageResource(R.drawable.sleep_on);
					Toast.makeText(getContext(), "going to sleep", Toast.LENGTH_SHORT).show();
					cat.setImageResource(R.drawable.sleeping_cat);

				}
				else {
					sleep.setImageResource(R.drawable.sleep_off);
					Toast.makeText(getContext(), "waking up", Toast.LENGTH_SHORT).show();
					cat.setImageResource(R.drawable.giphy);
				}
			}
		});

		notification.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), Notification.class);
				startActivity(intent);
			}
		});

		mic.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
					mic.setImageResource(R.drawable.mic_on);
					// Here, thisActivity is the current activity
					if (ContextCompat.checkSelfPermission(getContext(),
							Manifest.permission.RECORD_AUDIO)
							!= PackageManager.PERMISSION_GRANTED) {

						// Permission is not granted
						// Should we show an explanation?
						if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) getContext(),
								Manifest.permission.RECORD_AUDIO)) {
							// Show an explanation to the user *asynchronously* -- don't block
							// this thread waiting for the user's response! After the user
							// sees the explanation, try again to request the permission.
						} else {
							// No explanation needed; request the permission
							ActivityCompat.requestPermissions((Activity) getContext(),
									new String[]{Manifest.permission.RECORD_AUDIO},MY_PERMISSIONS_REQUEST_RECORD_AUDIO);

							// MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
							// app-defined int constant. The callback method gets the
							// result of the request.
						}
					} else {
						// Permission has already been granted
						Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
						intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
						intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,1);
						speechRecognizer.startListening(intent);
					}
				}
		});

		initializeTextToSpeech();
		initializeSpeechRecognizer();

		return V;
	}

	private void initializeSpeechRecognizer() {
		if (SpeechRecognizer.isRecognitionAvailable(getContext())) {
			speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getContext());
			speechRecognizer.setRecognitionListener(new RecognitionListener() {
				@Override
				public void onReadyForSpeech(Bundle params) {

				}

				@Override
				public void onBeginningOfSpeech() {

				}

				@Override
				public void onRmsChanged(float rmsdB) {

				}

				@Override
				public void onBufferReceived(byte[] buffer) {

				}

				@Override
				public void onEndOfSpeech() {

				}

				@Override
				public void onError(int error) {

				}

				@Override
				public void onResults(Bundle results) {
					List<String> result_arr = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
					mic.setImageResource(R.drawable.mic_off);
					processResult(result_arr.get(0));
				}

				@Override
				public void onPartialResults(Bundle partialResults) {

				}

				@Override
				public void onEvent(int eventType, Bundle params) {

				}
			});
		}
	}

	private void processResult(String result_message) {
		result_message = result_message.toLowerCase();

		Toast.makeText(getContext(),result_message,Toast.LENGTH_LONG).show();
		if(result_message.indexOf("what") != -1){
			if(result_message.indexOf("your name") != -1){
				speak("My Name is Mr.Auto Vacc. You can call me A C R");
			}
			if (result_message.indexOf("time") != -1){
				String time_now = DateUtils.formatDateTime(getContext(), new Date().getTime(),DateUtils.FORMAT_SHOW_TIME);
				speak("The time is now: " + time_now);
			}
			if (result_message.indexOf("master") != -1){
				speak("My master name is leftenant cornel Mr. Nazrul");
			}
		}
		else if (result_message.indexOf("start cleaning") != -1){
			speak("Cleaning Started");
		}
		else if (result_message.indexOf("stop cleaning") != -1) {
			speak("Cleaning Stopped");
		}
		else{
			speak("please repeat the command");
		}
	}

	private void initializeTextToSpeech() {
		textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
			@Override
			public void onInit(int status) {
				if (textToSpeech.getEngines().size() == 0 ){
					Toast.makeText(getContext(),"Say something",Toast.LENGTH_LONG).show();
					//finish();
				} else {
					textToSpeech.setLanguage(Locale.US);
					//speak("Hello there, I am ready to start our conversation");
				}
			}
		});
	}

	private void speak(String message) {
		if(Build.VERSION.SDK_INT >= 21){
			textToSpeech.speak(message,TextToSpeech.QUEUE_FLUSH,null,null);
		} else {
			textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH,null);
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		textToSpeech.shutdown();
	}

	@Override
	public void onResume() {
		super.onResume();
		initializeSpeechRecognizer();
		initializeTextToSpeech();
	}
}

