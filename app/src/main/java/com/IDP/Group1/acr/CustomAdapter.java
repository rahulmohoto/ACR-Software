package com.IDP.Group1.acr;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class CustomAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ImageView item;
    public String[] floor_value;
    int num_row,num_col,num_items;

    public CustomAdapter(Context context, int num_row, int num_col, String[][] floor_value) {
        this.context = context;
        this.num_row = num_row;
        this.num_col = num_col;
        this.num_items=num_row*num_col;
        this.floor_value = new String[num_items];
        for (int i=0; i<num_row; i++) {
            for (int j=0; j<num_col; j++) {
                this.floor_value[(i*num_col) + j] = floor_value[i][j];
            }
        }
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return num_items;
    }

    @Override
    public Object getItem(int i) {
        return floor_value[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup viewGroup) {
        if (view==null) {
            view=this.inflater.inflate(R.layout.floor_item, null);
            item=view.findViewById(R.id.item);
            Drawable tile;
            if (floor_value[position] == "1") {
                tile = this.context.getDrawable(R.drawable.tile_area);
            } else if (floor_value[position] == "2") {
                tile = this.context.getDrawable(R.drawable.tile_wall);
            } else {
                tile = this.context.getDrawable(R.drawable.tile);
            }
            this.item.setImageDrawable(tile);
        }
        return view;
    }
}
