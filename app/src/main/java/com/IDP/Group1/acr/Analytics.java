package com.IDP.Group1.acr;


import android.app.Activity;
import android.app.Dialog;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Analytics extends Fragment {
	int[] performance ={R.drawable.stat1,R.drawable.stat2,R.drawable.stat3,R.drawable.stat4};
	String[] val={"Battery","Dust","Clean rate","Power on time"};
	private ArrayAdapter<String> adapter;
	Activity act;

	public Analytics() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_analytics, container, false);

		GridView G=view.findViewById(R.id.grid);
		act = getActivity();

		status_cust_adapter adapter= new status_cust_adapter(act,performance,val);
		G.setAdapter(adapter);

		G.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				if (i == 0) {	//	battery
					Intent intent = new Intent(getContext(), BatteryPlot.class);
					startActivity(intent);

//					Dialog dialog = new Dialog(getContext());
//					dialog.setContentView(R.layout.activity_battery_plot);
//					dialog.show();
				}
				else if (i == 1) {	//	dust
					Intent intent = new Intent(getContext(), DustPlot.class);
					startActivity(intent);
				}
				else if (i == 2) {	//	cleaning Rate
					Intent intent = new Intent(getContext(), CleanRatePlot.class);
					startActivity(intent);
				}
				else if (i == 3) {	//	power on time
					Intent intent = new Intent(getContext(), PowerOnTimePlot.class);
					startActivity(intent);
				}
			}
		});
		return view;
	}

}
