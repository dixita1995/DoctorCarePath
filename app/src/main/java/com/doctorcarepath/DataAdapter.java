package com.doctorcarepath;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import static com.doctorcarepath.R.*;

/**
 * Created by Om on 28/4/2020.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<Tutorial> android;
    private Context mCtx;
    onClickInterface onClickInterface;
    public DataAdapter(List<Tutorial> tutorialList, Context mCtx,onClickInterface onclick) {
        this.android = tutorialList;
        this.mCtx = mCtx;
       this.onClickInterface=onclick;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_doctor, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.tv_doctor.setText(android.get(i).getName());
        viewHolder.tv_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickInterface.setClick(android.get(i).getSpecialists(),android.get(i).getName(),android.get(i).getImage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_doctor;
        public ViewHolder(View view) {
            super(view);
            tv_doctor = (TextView) view.findViewById(R.id.tv_doctor);
        }
    }
}
