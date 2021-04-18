package com.IDP.Group1.acr;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class frag_feedback extends Fragment {

	private EditText name, email, feedback;
	private Button send;
	private Toolbar feedbackToolbar;

	public frag_feedback() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_frag_feedback, container, false);

		name = (EditText) view.findViewById(R.id.feedbackNameID);
		email = (EditText) view.findViewById(R.id.feedbackEmailID);
		feedback = (EditText) view.findViewById(R.id.feedbackTextID);

		send = (Button) view.findViewById(R.id.feedbackSendID);

		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try {
					Intent intent = new Intent(Intent.ACTION_SEND);
					intent.setType("text/email");

					intent.putExtra(Intent.EXTRA_EMAIL, "wazedrifat@gmail.com");
					intent.putExtra(Intent.EXTRA_SUBJECT, "feedback from " + name.getText().toString());
					intent.putExtra(Intent.EXTRA_TEXT, email.getText().toString() + "\n" + feedback.getText().toString());

					startActivity(Intent.createChooser(intent, "Feedback with"));
				}
				catch (Exception e){
					Toast.makeText(getContext(), "Exception : " + e.toString(), Toast.LENGTH_SHORT).show();
				}
			}
		});

		return view;
	}
}
