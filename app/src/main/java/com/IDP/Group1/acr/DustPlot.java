package com.IDP.Group1.acr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.DatePicker;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class DustPlot extends AppCompatActivity {
	BarChart barChart;
	User user;
	int day, month;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dust_plot);

		barChart = findViewById(R.id.barChartID);
		user = new User();

		DatePicker datePicker = new DatePicker(this);
		day = datePicker.getDayOfMonth();
		month = datePicker.getMonth();

		barChart.setDragEnabled(true);
		barChart.getAxisLeft().setDrawGridLines(false);
		barChart.getAxisRight().setEnabled(false);
		barChart.getXAxis().setDrawGridLines(false);
		barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

		XAxis xAxis = barChart.getXAxis();
		xAxis.setValueFormatter(new MyValueFormatter1(day, month));

		ArrayList <BarEntry> data = user.getDustData();

		BarDataSet dataSet = new BarDataSet(data, "Dust amounts");
		dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
		dataSet.setValueFormatter(new MyValueFormatter1(day, month));

		BarData datas = new BarData(dataSet);
		datas.setBarWidth(0.5f);
		datas.setValueFormatter(new MyValueFormatter1(day, month));

		barChart.setData(datas);
	}
}

class MyValueFormatter1 extends ValueFormatter {

	int day, month;
	String [] monthName;
	int []dayofMonth;

	public MyValueFormatter1(int day, int month) {
		this.day = day;
		this.month = month;
		monthName = new String[12];

		monthName[0] = "Jan";
		monthName[1] = "Feb";
		monthName[2] = "Mar";
		monthName[3] = "Apr";
		monthName[4] = "May";
		monthName[5] = "Jun";
		monthName[6] = "Jul";
		monthName[7] = "Aug";
		monthName[8] = "Sep";
		monthName[9] = "Oct";
		monthName[10] = "Nov";
		monthName[11] = "Dec";

		dayofMonth = new int[] {
			31, 29, 31, 30, 31, 30, 31,
			31, 30, 31, 30, 31
		};
	}

	@Override
	public String getAxisLabel(float value, AxisBase axis) {

		int val = (int)value;

		if (val <= day) {
			day -= val;
		}
		else {
			int v = val - day;
			val -= v;

			month = (month + 11) % 12;

			day = dayofMonth[month] - val;

		}

		return day + " " + monthName[month];
	}

//	@Override
//	public String getPointLabel(Entry entry) {
//		return entry.getX() + "gm";
//	}

	@Override
	public String getBarLabel(BarEntry barEntry) {
		return barEntry.getY() + "gm";
	}
}