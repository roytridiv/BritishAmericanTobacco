package com.example.britishamericantobacco;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class ColorAdapter extends ArrayAdapter<ColorItem> {
    public ColorAdapter(@NonNull Context context, ArrayList<ColorItem> colorList) {
        super(context, 0 , colorList);
    }


    @NonNull
    @Override
    public View getView(int position,  @Nullable View convertView,  @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        return initView(position, convertView,parent);
    }

    @Override
    public View getDropDownView(int position,  @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getDropDownView(position, convertView, parent);
        return initView(position, convertView,parent);
    }

    private View initView (int position , View converView , ViewGroup parent){
        if(converView == null){
            converView = LayoutInflater.from(getContext()).inflate(R.layout.color_spinner_row,parent,false);
        }






        ImageView imageViewFlag = converView.findViewById(R.id.red);

        ColorItem colorItem = getItem(position);
        if (colorItem != null){
            imageViewFlag.setImageResource(colorItem.getmFlagImage());
        }

        return converView;
    }
}
