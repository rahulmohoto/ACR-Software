package com.IDP.Group1.acr;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class CustomExpandableListViewAdapter extends BaseExpandableListAdapter {

	private Context context;
	private List<SheduleClass> shedules;
	private CustomExpandableListViewAdapter adapter = this;
	private TextView text1, text2;
	private int curDay, curMonth, curYear;

	public CustomExpandableListViewAdapter(Context context, List<SheduleClass> shedules) {
		this.context = context;
		this.shedules = shedules;

		DatePicker datePicker = new DatePicker(context);
		curDay = datePicker.getDayOfMonth();
		curMonth = datePicker.getMonth();
		curYear = datePicker.getYear();
	}

	@Override
	public int getGroupCount() {
		return shedules.size();
	}

	@Override
	public int getChildrenCount(int i) {
		return 1;
	}

	@Override
	public Object getGroup(int i) {
		return shedules.get(i);
	}

	@Override
	public Object getChild(int i, int i1) {
		return shedules.get(i);
	}

	@Override
	public long getGroupId(int i) {
		return i;
	}

	@Override
	public long getChildId(int i, int i1) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(final int i, boolean b, View view, final ViewGroup viewGroup) {
		final SheduleClass sh = (SheduleClass) getGroup(i);
		int[] day = sh.getDay();

		if (view == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.layout_header, null);
		}

		TextView summary = view.findViewById(R.id.summaryTextID);

		if (sh.isType()) {
			summary.setText(getSummary(day));
		}
		else {
			summary.setText(getDate2(sh.getDate(), sh.getMonth(), sh.getYear()));
		}

		ImageView deleteButton = view.findViewById(R.id.deleteButtonID);

		deleteButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				shedules.remove(i);
				adapter.notifyDataSetChanged();
			}
		});

		text1 = view.findViewById(R.id.headerText1ViewID);
		text2 = view.findViewById(R.id.headerText2ViewID);

		text1.setText(formatTime(sh.getHour(), sh.getMinute()));

		if (sh.isAM)
			text2.setText("am");
		else
			text2.setText("pm");

		text1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getTime(sh);
			}
		});
		return view;
	}

	@Override
	public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
		Toast.makeText(context, "entering " + i, Toast.LENGTH_SHORT).show();

		final SheduleClass sh1 = (SheduleClass) getGroup(i);
		final int []day1 = sh1.getDay();

		if (sh1.date == -1) {
			sh1.setDate(curDay);
			sh1.setMonth(curMonth);
			sh1.setYear(curYear);
		}

		if (view == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.layout_footer, null);
		}

		final CheckBox repeatCheckBox = view.findViewById(R.id.repeatCheckBoxID);
		final LinearLayout weekLayout = view.findViewById(R.id.RepeatedID);
		final Button button = view.findViewById(R.id.datePickerButton);
		final Button sat = view.findViewById(R.id.buttonSatID);
		final Button sun = view.findViewById(R.id.buttonSunID);
		final Button mon = view.findViewById(R.id.buttonMonID);
		final Button tues = view.findViewById(R.id.buttonTuesID);
		final Button wed = view.findViewById(R.id.buttonWedID);
		final Button thur = view.findViewById(R.id.buttonThursID);
		final Button fri = view.findViewById(R.id.buttonFriID);

		setDay(sat, 0, i);
		setDay(sun, 1, i);
		setDay(mon, 2, i);
		setDay(tues, 3, i);
		setDay(wed, 4, i);
		setDay(thur, 5, i);
		setDay(fri, 6, i);

		if (sh1.isType()) {
			Toast.makeText(context, "true", Toast.LENGTH_SHORT).show();
			repeatCheckBox.setChecked(true);
			weekLayout.setVisibility(View.VISIBLE);
			button.setVisibility(View.GONE);
//			sum.setText(getSummary(day1));
		}
		else {
			Toast.makeText(context, "false", Toast.LENGTH_SHORT).show();
			repeatCheckBox.setChecked(false);
			weekLayout.setVisibility(View.GONE);
			button.setVisibility(View.VISIBLE);
			button.setText(getDate1(sh1.getDate(), sh1.getMonth(), sh1.getYear()));
//			sum.setText(getDate2(sh1.getDate(), sh1.getMonth(), sh1.getYear()));
		}
		
		repeatCheckBox.setOnCheckedChangeListener(	new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				sh1.setType(repeatCheckBox.isChecked());
				Toast.makeText(context, ":(" + i + " " + i1, Toast.LENGTH_SHORT).show();
				update(i, sh1, "checkbox 187");
				Toast.makeText(context, ":(" + i + " " + i1, Toast.LENGTH_SHORT).show();
			}
		});

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				DatePickerDialog dialog = new DatePickerDialog(
						context,
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker datePicker, int year, int month, int date) {
								button.setText(getDate1(date, month, year));
//								sum.setText(getDate2(date, month, year));
								sh1.setDate(date);
								sh1.setMonth(month);
								sh1.setYear(year);
								update(i, sh1, "datepicker 204");
							}
						},
						curYear,
						curMonth,
						curDay
				);

				dialog.show();
			}
		});

		return view;
	}

	private void setDay(final Button btn, final int i, final int id) {
		final SheduleClass sh = (SheduleClass) shedules.get(id);
		final int[] day = sh.getDay();
		if (day[i] == 1) {
			btn.setTextColor(Color.parseColor("#3A5351"));
			btn.setBackgroundResource(R.drawable.round_button_selected);
		}
		else {
			btn.setTextColor(Color.parseColor("#97F2F3F3"));
			btn.setBackgroundResource(R.drawable.round_button);
		}

		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				day[i] = 1 - day[i];

				sh.setDay(day);
				update(id, sh, "btn 237");
			}
		});
	}

	private String getDate1(int day, int month, int year) {
		String a = "", b = "";

		month++;

		if (day < 10)		a = "0";
		if (month < 10)		b = "0";

		return (a + day + "/" + b + month + "/" + year);
	}

	private String getDate2(int day, int month, int year) {
		final String []name;
		name = new String[12];

		name[0] = "Jan";
		name[1] = "Feb";
		name[2] = "Mar";
		name[3] = "Apr";
		name[4] = "May";
		name[5] = "Jun";
		name[6] = "Jul";
		name[7] = "Aug";
		name[8] = "Sep";
		name[9] = "Oct";
		name[10] = "Nov";
		name[11] = "Dec";

		String a = "", b = "";

		if (day < 10)		a = "0";

		return (a + day + "/" + name[month] + "/" + year);
	}


	private String getSummary(int[] day) {
		boolean start = true;

		String s = " ";
		String []name;

		name = new String[7];

		name[0] = "Sat";
		name[1] = "Sun";
		name[2] = "Mon";
		name[3] = "Tues";
		name[4] = "Wed";
		name[5] = "Thu";
		name[6] = "Fri";

		for (int i = 0; i < 7; i++) {

			if (day[i] == 1) {
				if (!start) {
					s += ", ";
				}

				s += name[i];

				start = false;
			}
		}

		if (start) {
			return "No day selected!";
		}
		return s;
	}

	private void getTime(final SheduleClass sh) {
		TimePicker timePicker = new TimePicker(context);
		final int curHour = timePicker.getCurrentHour();
		final int curMinute = timePicker.getCurrentMinute();

		TimePickerDialog dialog = new TimePickerDialog(
				context,
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

						text1.setText(formatTime(i, i1));
						if (isAM)
							text2.setText("am");
						else
							text2.setText("pm");
					}
				},
				curHour,
				curMinute,
				false
		);
		dialog.show();
	}

	String formatTime(int hour, int minute) {
		String a = "", b = "";

		if (hour < 10)
			a = "0";

		if (minute < 10)
			b = "0";

		String res = a + hour + ":" + b + minute;

		return  res;
	}

	void update(int i, SheduleClass s, String from) {
		Toast.makeText(context, "updating " + from + " " + i, Toast.LENGTH_SHORT).show();
		shedules.set(i, s);
		adapter.notifyDataSetChanged();
	}
	@Override
	public boolean isChildSelectable(int i, int i1) {
		return false;
	}
}
