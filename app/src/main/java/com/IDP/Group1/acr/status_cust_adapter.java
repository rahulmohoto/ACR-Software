package com.IDP.Group1.acr;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class status_cust_adapter extends BaseAdapter {

	Context context;
	int[] performance;
	String[] val;
	private LayoutInflater inflater;

	status_cust_adapter(Context context, int[] performance, String[] val){
		this.context=context;
		this.val=val;
		this.performance=performance;

	}

	@Override
	public int getCount() {
		return val.length;
	}

	@Override
	public Object getItem(int i) {
		return null;
	}

	@Override
	public long getItemId(int i) {
		return 0;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		if(view == null)
		{
			inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			view=inflater.inflate(R.layout.adapter_status,viewGroup,false);
		}

		ImageView img=view.findViewById(R.id.imgstatus);
		TextView txt=view.findViewById(R.id.txtstatus);

		img.setImageResource(performance[i]);
		txt.setText(val[i]);


		return view;
	}
}
