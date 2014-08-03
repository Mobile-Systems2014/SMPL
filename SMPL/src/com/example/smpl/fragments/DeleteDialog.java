package com.example.smpl.fragments;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.application.smpl.database.adapter.StartUpDataBaseAdapter;
import com.application.smpl.qr_reader.DecoderActivity;
import com.example.smpl.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class DeleteDialog extends DialogFragment {
	
	StartUpDataBaseAdapter DB;
	String[] list;
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	
    	DB = new StartUpDataBaseAdapter(getActivity());
    	List<HashMap<String, String>> nameList = DB.GetShoppingList();
    	
    	int index = 0;
    	if (nameList != null) {
    	    for (HashMap<String, String> map : nameList) {
    	        for (Entry<String, String> mapEntry : map.entrySet()){
    	            list[index] = mapEntry.getValue();
    	            index++;
    	        }
    	    }
    	}
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Remove")
               .setItems(list, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
                                     
               }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
