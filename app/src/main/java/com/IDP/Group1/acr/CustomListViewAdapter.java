package com.IDP.Group1.acr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class CustomListViewAdapter extends BaseAdapter {

	private Context context;
	private List<CleanHistory> list;

	public CustomListViewAdapter(Context context, List<CleanHistory> list) {
		this.context = context;
		this.list = list;
	}

	public CustomListViewAdapter() {
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.layout_cleanviewitem, null);
		}



		return convertView;
	}
}
