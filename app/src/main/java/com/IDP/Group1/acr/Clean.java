package com.IDP.Group1.acr;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Clean extends Fragment {

	ListView listView;
	FloatingActionButton floatingActionButton;

	public Clean() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view =  inflater.inflate(R.layout.fragment_clean, container, false);

		listView = view.findViewById(R.id.cleanListViewID);

		List<CleanHistory> list;
		list = new ArrayList<>();

		list.add(new CleanHistory(03, 35, 23, 1, 2020, 173));
		list.add(new CleanHistory(03, 35, 23, 1, 2020, 173));
		list.add(new CleanHistory(03, 35, 23, 1, 2020, 173));

		Toast.makeText(view.getContext(), list.size() + " ", Toast.LENGTH_SHORT).show();

		CustomListViewAdapter customListViewAdapter = new CustomListViewAdapter(view.getContext(), list);
		listView.setAdapter(customListViewAdapter);

		floatingActionButton = view.findViewById(R.id.floatingActionButtonID);

		floatingActionButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				View view = getActivity().findViewById(R.id.nav_host_fragment);
				Snackbar.make(view, "Cleaning Right Now. Please Wait", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});
		return view;
	}

}
