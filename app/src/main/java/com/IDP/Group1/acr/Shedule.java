package com.IDP.Group1.acr;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Shedule extends Fragment {

	private CustomExpandableListViewAdapter adapter;
	View lastClicked;
	int prevExpanded;
	private User user;

	public Shedule() {
		// Required empty public constructor
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, final ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		final View view = inflater.inflate(R.layout.fragment_shedule, container, false);

		final ExpandableListView expandableListView = view.findViewById(R.id.expandableListViewID);

		user = new User();
//		expandableListView.setGroupIndicator(null);

		final List<SheduleClass> sheduleList = user.getShedules();

		prevExpanded = -1;
		expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
			@Override
			public void onGroupExpand(int groupPosition) {
				if (prevExpanded != -1 && prevExpanded != groupPosition) {
					expandableListView.collapseGroup(prevExpanded);
					LinearLayout prevLayout = expandableListView.getChildAt(prevExpanded).findViewById(R.id.headerID);
					if (prevLayout != null) {
						prevLayout.setBackgroundColor(Color.parseColor("#202124"));
					}

//					Toast.makeText(getContext(), "prev = " + prevExpanded, Toast.LENGTH_SHORT).show();
				}
				LinearLayout curLayout = expandableListView.getChildAt(groupPosition).findViewById(R.id.headerID);

				if (curLayout != null) {
					curLayout.setBackgroundColor(Color.parseColor("#2A2A30"));
				}
				else {
//					Toast.makeText(getContext(), "null = " + groupPosition, Toast.LENGTH_SHORT).show();
				}

//				Toast.makeText(getContext(), "pos = " + groupPosition, Toast.LENGTH_SHORT).show();
				prevExpanded = groupPosition;
			}
		});

		expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
			@Override
			public void onGroupCollapse(int groupPosition) {
				LinearLayout layout = expandableListView.getChildAt(prevExpanded).findViewById(R.id.headerID);

				if (layout != null) {
					layout.setBackgroundColor(Color.parseColor("#202124"));
				}
			}
		});
		
		adapter = new CustomExpandableListViewAdapter(view.getContext(), sheduleList);
		expandableListView.setAdapter(adapter);

		FloatingActionButton fab = view.findViewById(R.id.fabSheduleID);

		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View view) {
				DatePicker datePicker = new DatePicker(getContext());
				final int curDay = datePicker.getDayOfMonth();
				final int curMonth = datePicker.getMonth() + 1;
				final int curYear = datePicker.getYear();

				TimePicker timePicker = new TimePicker(getContext());
				final int curHour = timePicker.getCurrentHour();
				final int curMinute = timePicker.getCurrentMinute();

				TimePickerDialog dialog = new TimePickerDialog(
						getContext(),
						new TimePickerDialog.OnTimeSetListener() {
							@Override
							public void onTimeSet(TimePicker timePicker, int i, int i1) {
								boolean isAM = true;
								if (i > 12) {
									isAM = false;
									i -= 12;
								}
								else if (i == 0) {
									i = 12;
								}

								sheduleList.add(new SheduleClass(i, i1, curDay ,curMonth, curYear, isAM));
								user.setShedules(sheduleList);
								adapter.notifyDataSetChanged();
							}
						},
						curHour,
						curMinute,
						false
				);
				dialog.show();
			}
		});
		return view;
	}
}
