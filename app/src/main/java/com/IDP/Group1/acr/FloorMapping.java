package com.IDP.Group1.acr;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FloorMapping extends Fragment {

	private ConstraintLayout constraintLayout;
	private GridView floor_grid;
	Spinner spinner;
	private Button setgrid_btn;
	private int x_limit, y_limit, rawWidth=05, count=0, num_row, num_col,current_spinner_item=0;
	public String[][] floor_value;
	public String[] spinner_item;
	public String send_value;

	DisplayMetrics metrics;

	public FloorMapping() {
		// Required empty public constructor
		num_row=num_col=50;
		this.floor_value = new String[this.num_row][this.num_col];
		clear_floor_value();
		this.send_value="";
		this.spinner_item = new String[]{"Wall", "Area"};
		this.current_spinner_item=0;
	}

	public FloorMapping(int length,int width){
		this.num_col=length;
		this.num_row=width;
		this.floor_value = new String[this.num_row][this.num_col];
		clear_floor_value();
		this.send_value="";
		this.spinner_item = new String[]{"Wall", "Area"};
		this.current_spinner_item=0;
	}

	public FloorMapping(int length,int width,String[][] floor_value,int current_spinner_item){
		this.num_col=length;
		this.num_row=width;
		this.floor_value = floor_value;
		//clear_floor_value();
		this.send_value="";
		this.spinner_item=spinner_item;
		this.spinner_item = new String[]{"Wall", "Area"};
		this.current_spinner_item=current_spinner_item;
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
//		Toast.makeText(getContext(),"On Create View",Toast.LENGTH_SHORT).show();
		View V=inflater.inflate(R.layout.fragment_floor_mapping, container, false);

		constraintLayout=V.findViewById(R.id.cLayout);
		floor_grid=V.findViewById(R.id.floor_grid);
		setgrid_btn=V.findViewById(R.id.setgrid_btn);
		spinner=V.findViewById(R.id.send_btn);

		ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),R.layout.spinner_item,R.id.spinner_item,this.spinner_item);
		spinner.setAdapter(arrayAdapter);
		if (this.current_spinner_item==1) {
			this.spinner.setSelection(1);
		}

		floor_grid.setAdapter(new CustomAdapter(getContext(), num_row, num_col, floor_value));
		floor_grid.setNumColumns(num_col);
		metrics = getResources().getDisplayMetrics();
		floor_grid.setHorizontalSpacing(0);
		floor_grid.setVerticalSpacing(0);

		constraintLayout.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				setgrid_btn.setText("on CL");
				return true;
			}
		});

		floor_grid.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				x_limit=floor_grid.getColumnWidth()*floor_grid.getNumColumns();
				y_limit=(int)(Math.ceil((num_row*num_col)/floor_grid.getNumColumns())*metrics.density*rawWidth);
				//int densityDpi = (int)(metrics.density * 160f);
				//set_btn.setText(String.valueOf(x_limit)+" "+String.valueOf(y_limit));
				int index = motionEvent.getActionIndex();
				int action = motionEvent.getActionMasked();
				int pointerId = motionEvent.getPointerId(index);

				int x= (int) motionEvent.getX();
				int y= (int) motionEvent.getY();

				int col = (int) (x / floor_grid.getColumnWidth());
				int row = (int) (y / (metrics.density * rawWidth));
				//setgrid_btn.setText(String.valueOf(index) + " " + String.valueOf(row) + " " + String.valueOf(col));
				//set_btn.setText(String.valueOf(index) + " " + String.valueOf(x) + " " + String.valueOf(y));
				if((row>=0 && row<num_row) && (col>=0 && col<num_col)){
					String tile = spinner.getSelectedItem().toString();
					if (tile.matches("Wall")) {
						FloorMapping.this.floor_value[row][col] = "1";
					} else if (tile.matches("Area")) {
						FloorMapping.this.floor_value[row][col] = "2";
					}
				}

				if (motionEvent.getAction()==MotionEvent.ACTION_UP){
					//Toast.makeText(getContext(),"Array Set",Toast.LENGTH_SHORT).show();
					String st="";
					for(int i=0;i<num_row;i++){
						for (int j=0;j<num_col;j++){
							st=st.concat(floor_value[i][j]);
						}
						st=st.concat("\n");
					}
					send_value=st;
//					Toast.makeText(getContext(),send_value.trim(),Toast.LENGTH_SHORT).show();
					//clear_floor_value();
					floor_grid_reset();
				}
				return true;
			}
		});
		setgrid_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
				View gview = getLayoutInflater().inflate(R.layout.dialog_set_grid, null);
				final EditText length_etxt = (EditText) gview.findViewById(R.id.length_etxt);
				final EditText width_etxt = (EditText) gview.findViewById(R.id.width_etxt);
				builder.setView(gview);
				builder.setPositiveButton(R.string.dialog_setFloor_change, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						String length = String.valueOf(length_etxt.getText());
						String width = String.valueOf(width_etxt.getText());
						if (length.isEmpty()||width.isEmpty()) {
//							Toast.makeText(getContext(),"Grid Unchanged for Invalid Input",Toast.LENGTH_SHORT).show();
						}
						else {
							FragmentManager fragmentManager = getFragmentManager();
							FloorMapping floorMapping = new FloorMapping(Integer.parseInt(length.trim()), Integer.parseInt(width.trim()));
							fragmentManager.beginTransaction()
									.replace(R.id.nav_host_fragment, floorMapping).commit();
						}
					}
				})
						.setNegativeButton(R.string.dialog_setFloor_cancel, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
//								Toast.makeText(getContext(),"Grid Unchanged",Toast.LENGTH_SHORT).show();
							}
						});
				final AlertDialog dialog = builder.create();
				dialog.show();
			}
		});
		return V;
	}

	@Override
	public void onPause() {
		super.onPause();
//		Toast.makeText(getContext(),"On Pause",Toast.LENGTH_SHORT).show();
	}

	private void floor_grid_reset() {
		String tile = spinner.getSelectedItem().toString();
		if (tile.matches("Wall")) {
			current_spinner_item = 0;
		} else if (tile.matches("Area")) {
			current_spinner_item = 1;
		}
		FragmentManager fragmentManager = getFragmentManager();
		FloorMapping floorMapping = new FloorMapping(this.num_row, this.num_col, this.floor_value, current_spinner_item);
		fragmentManager.beginTransaction()
				.replace(R.id.nav_host_fragment, floorMapping).commit();

	}

	private void clear_floor_value() {
		for(int i=0;i<num_row;i++){
			for (int j=0;j<num_col;j++){
				floor_value[i][j]="0";
			}
		}
		return;
	}
}
