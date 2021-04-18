package com.IDP.Group1.acr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class feedback extends actionBar {

    private EditText name, email, feedback;
    private Button send;
    private Toolbar feedbackToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedbackToolbar = (Toolbar) findViewById(R.id.feedbackToolbarID);
        setSupportActionBar(feedbackToolbar);

        name = (EditText) findViewById(R.id.feedbackNameID);
        email = (EditText) findViewById(R.id.feedbackEmailID);
        feedback = (EditText) findViewById(R.id.feedbackTextID);

        send = (Button) findViewById(R.id.feedbackSendID);

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
                    Toast.makeText(getApplicationContext(), "Exception : " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
