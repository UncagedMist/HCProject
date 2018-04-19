package com.techbytecare.kk.healthcareproject.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.techbytecare.kk.healthcareproject.R;

/**
 * Created by kundan on 2/26/2018.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView txtField,txtValue,txtDate;

    public ViewHolder(View itemView) {
        super(itemView);

        txtField = itemView.findViewById(R.id.txtField);
        txtValue = itemView.findViewById(R.id.txtValue);
        txtDate = itemView.findViewById(R.id.txtDate);
    }
}
