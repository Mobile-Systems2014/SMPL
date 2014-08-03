package com.example.smpl.fragments;

import com.application.smpl.database.adapter.DBAdapter;
import com.application.smpl.database.adapter.StartUpDataBaseAdapter;
import com.application.smpl.qr_reader.DecoderActivity;
import com.example.smpl.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class ProductDialog extends DialogFragment {
	
	StartUpDataBaseAdapter DB;
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	final String[] products = new String[]{"112","113","114","115","116","117","118","119","120","121"};
    	DB = new StartUpDataBaseAdapter(getActivity());
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Item")
               .setItems(R.array.product_array, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
                   for(int index = 0; index < products.length; index++)
                   {
                	    if(which == index)
                	    {
                	    	DB.SaveToList(products[index]);
                	    }
                   }
               }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
