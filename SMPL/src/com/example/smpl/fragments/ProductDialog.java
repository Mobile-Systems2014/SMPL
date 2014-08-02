package com.example.smpl.fragments;

import com.application.smpl.qr_reader.DecoderActivity;
import com.example.smpl.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class ProductDialog extends DialogFragment {
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Item")
               .setItems(R.array.product_array, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
                                   
               }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
