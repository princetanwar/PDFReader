package com.loveinshayari.pdfreader.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.loveinshayari.pdfreader.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class PDFadptor extends ArrayAdapter<File> {

    Context context;
    ViewHolder viewHolder;
    ArrayList<File> al_pdf;


    public PDFadptor(@NonNull Context context, ArrayList<File> al_pdf) {
        super(context, R.layout.adptor_pdf,al_pdf);
        this.context = context;
        this.al_pdf = al_pdf;
    }


    @Override
    public int getItemViewType(int position) {
        if (al_pdf.size() < 0){
            return al_pdf.size();
        }

        else
            return 1;
    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adptor_pdf,parent,false);
            viewHolder = new ViewHolder();

            viewHolder.tv_filename = convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }

        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_filename.setText(al_pdf.get(position).getName());
        return convertView;
    }

    public class ViewHolder{

        TextView tv_filename;
    }
}
