package com.IDP.Group1.acr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MotionEvent;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class BatteryPlot extends AppCompatActivity {

	LineChart mchart;
	User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battery_plot);

//		Toolbar toolbar = (Toolbar) findViewById(R.id.aboutToolbarID);
//		setSupportActionBar(toolbar);

		mchart = findViewById(R.id.lineChartID);
		mchart.setDragEnabled(true);
//		mchart.setScaleEnabled(true);
		mchart.getAxisLeft().setDrawGridLines(false);
		mchart.getAxisRight().setEnabled(false);
		mchart.getXAxis().setDrawGridLines(false);
		mchart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
		user = new User();

		ArrayList<Entry> values = user.getBatteryData();

		XAxis xAxis = mchart.getXAxis();
		xAxis.setValueFormatter(new MyValueFormatter());

		LineDataSet lineDataSet = new LineDataSet(values, "Battery Level");
		lineDataSet.setFillAlpha(110);
		lineDataSet.setDrawFilled(true);
		lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
		lineDataSet.setValueFormatter(new MyValueFormatter());
//		lineDataSet.setColor(R.color.ash);

		ArrayList <ILineDataSet> lineDataSets = new ArrayList<>();
		lineDataSets.add(lineDataSet);

//		String[] times = {"6.00am", "7.00am", "10.00am", "12.00pm", "2.00pm", "3.00pm", "4.00pm", "8.00pm"};
		LineData data = new LineData(lineDataSets);
		data.setValueFormatter(new MyValueFormatter());
		mchart.setData(data);
	}
}

class MyValueFormatter extends ValueFormatter {
	@Override
	public String getAxisLabel(float value, AxisBase axis) {
		int time = (int)value;
		String tail = ".00am";

		if (time >= 12) {
			tail = ".00pm";
			time %= 12;
		}

		if (time == 0) {
			time = 12;
		}

		return time + tail;
	}

	@Override
	public String getPointLabel(Entry entry) {
		return entry.getX() + "%";
	}
}