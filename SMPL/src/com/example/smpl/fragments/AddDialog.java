package com.example.smpl.fragments;

import com.application.smpl.qr_reader.DecoderActivity;
import com.example.smpl.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class AddDialog extends DialogFragment {
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose")
               .setItems(R.array.options_array, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
                   if(which == 0)
                   {
                	   Intent intentStart = new Intent(getActivity(),DecoderActivity.class);
                       startActivity(intentStart);
                   }
                   else
                   {
                	   ProductDialog productDialog = new ProductDialog();
                       productDialog.show(getFragmentManager(), "ProductDialog");
                   }                   
               }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
