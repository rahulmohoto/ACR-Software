package com.IDP.Group1.acr;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link KidMode.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link KidMode#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KidMode extends Fragment implements View.OnTouchListener, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    FrameLayout frameLayout;
    ConstraintLayout constraintLayout;
    ImageView up_btn, left_btn, right_btn, down_btn, suct_btn;
    TextView debug, sonar_mostleft, sonar_left, sonar_mid, sonar_right, sonar_mostright;
    FirebaseDatabase database;
    DatabaseReference up_btn_ref, left_btn_ref, right_btn_ref, down_btn_ref, suct_btn_ref, sonar_ref;
    int suct;

    public KidMode() {
        // Required empty public constructor
        suct=0;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KidMode.
     */
    // TODO: Rename and change types and number of parameters
    public static KidMode newInstance(String param1, String param2) {
        KidMode fragment = new KidMode();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toast.makeText(getContext(),"On Create View",Toast.LENGTH_LONG).show();
        View V=inflater.inflate(R.layout.fragment_kid_mode, container, false);
        frameLayout=V.findViewById(R.id.main_layout);
        constraintLayout=V.findViewById(R.id.sub_layout);
        up_btn=V.findViewById(R.id.up_btn);
        left_btn=V.findViewById(R.id.left_btn);
        right_btn=V.findViewById(R.id.right_btn);
        down_btn=V.findViewById(R.id.down_btn);
        suct_btn=V.findViewById(R.id.suct_btn);

        debug=V.findViewById(R.id.debug);
        sonar_mostleft=V.findViewById(R.id.sonar_mostleft);
        sonar_left=V.findViewById(R.id.sonar_left);
        sonar_mid=V.findViewById(R.id.sonar_mid);
        sonar_right=V.findViewById(R.id.sonar_right);
        sonar_mostright=V.findViewById(R.id.sonar_mostright);

        up_btn.setOnTouchListener(this);
        left_btn.setOnTouchListener(this);
        right_btn.setOnTouchListener(this);
        down_btn.setOnTouchListener(this);
        //down_btn.setOnTouchListener(this);

        suct_btn.setOnClickListener(this);
        constraintLayout.setOnClickListener(this);

        initializeFirebase();

        return  V;
    }

    @Override
    public void onPause() {
        super.onPause();
        database.goOffline();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        database.goOnline();
        //initializeFirebase();
    }

    private void initializeFirebase() {

        database = FirebaseDatabase.getInstance();
        up_btn_ref = database.getReference("Button/up_btn");
        left_btn_ref = database.getReference("Button/left_btn");
        right_btn_ref = database.getReference("Button/right_btn");
        down_btn_ref = database.getReference("Button/down_btn");
        suct_btn_ref = database.getReference("Button/suction");

        sonar_ref = database.getReference("Sonar");
        sonar_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //System.out.println("Data change");
                Sonar sonar = dataSnapshot.getValue(Sonar.class);
                debug.setText("Data Changed");
                //Log.d(TAG, "Value is: " + value);
                sonar_mostleft.setText(getString(R.string.sonar_mostleft)+": "+String.valueOf(sonar.getSonar_mostleft()));
                sonar_left.setText(getString(R.string.sonar_left)+": "+String.valueOf(sonar.getSonar_left()));
                sonar_mid.setText(getString(R.string.sonar_mid)+": "+String.valueOf(sonar.getSonar_mid()));
                sonar_right.setText(getString(R.string.sonar_right)+": "+String.valueOf(sonar.getSonar_right()));
                sonar_mostright.setText(getString(R.string.sonar_mostright)+": "+String.valueOf(sonar.getSonar_mostright()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {
            case R.id.up_btn:
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    up_btn.setImageResource(R.drawable.touched_up_btn);
                    debug.setText("Up Btn Pressed");
                    up_btn_ref.setValue(1);
                }
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    up_btn.setImageResource(R.drawable.up_btn);
                    debug.setText("Up Btn Released");
                    up_btn_ref.setValue(0);
                }
                return true;
            case R.id.left_btn:
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    left_btn.setImageResource(R.drawable.touched_left_btn);
                    debug.setText("Left Btn Pressed");
                    left_btn_ref.setValue(1);
                }
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    left_btn.setImageResource(R.drawable.left_btn);
                    debug.setText("Left Btn Released");
                    left_btn_ref.setValue(0);
                }
                return true;
            case R.id.right_btn:
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    right_btn.setImageResource(R.drawable.touched_right_btn);
                    debug.setText("Right Btn Pressed");
                    right_btn_ref.setValue(1);
                }
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    right_btn.setImageResource(R.drawable.right_btn);
                    debug.setText("Right Btn Released");
                    right_btn_ref.setValue(0);
                }
                return true;
            case R.id.down_btn:
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    down_btn.setImageResource(R.drawable.touched_down_btn);
                    debug.setText("Down Btn Pressed");
                    down_btn_ref.setValue(1);
                }
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    down_btn.setImageResource(R.drawable.down_btn);
                    debug.setText("Down Btn Released");
                    down_btn_ref.setValue(0);
                }
                return true;
            default:
                return true;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.suct_btn:
                if (suct == 0){
                    suct_btn.setImageResource(R.drawable.stop_btn);
                    suct=1;
                }
                else {
                    suct_btn.setImageResource(R.drawable.start_btn);
                    suct=0;
                }
                suct_btn_ref.setValue(suct);
        }
        return;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        /*if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
