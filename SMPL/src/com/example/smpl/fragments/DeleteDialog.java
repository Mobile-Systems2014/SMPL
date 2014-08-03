package com.example.smpl.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import android.util.Log;
import android.widget.Toast;

public class DeleteDialog extends DialogFragment {

	StartUpDataBaseAdapter DB;
	ArrayList<String> arrayList = new ArrayList<String>();

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		DB = new StartUpDataBaseAdapter(getActivity());
		List<HashMap<String, String>> nameList = DB.GetShoppingList();
		boolean flag = true;
		if (nameList != null) {
			for (HashMap<String, String> map : nameList) {
				for (Entry<String, String> mapEntry : map.entrySet()) {
					if (flag) {
						arrayList.add(mapEntry.getValue());
						flag = false;
					} else {
						flag = true;
					}
				}
			}
		}
		final String[] array = arrayList.toArray(new String[arrayList.size()]);
		final Map<String, String> map = new HashMap<String, String>();
		map.put("apple", "112");
		map.put("bread", "113");
		map.put("pizza", "114");
		map.put("orange", "115");
		map.put("milk", "116");
		map.put("eggs", "117");
		map.put("butter", "118");
		map.put("chicken", "119");
		map.put("fish", "120");
		map.put("sprite", "121");

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Remove").setItems(array,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						for(int index = 0; index < array.length; index++)
						{
							if(index == which)
							{
								Iterator it = map.entrySet().iterator();
							    while (it.hasNext()) {							
							        Map.Entry pairs = (Map.Entry)it.next();
							        if(pairs.getKey().toString().equalsIgnoreCase(array[index].toString()))
							    	{
							        	DB.RemoveFromList(pairs.getValue().toString());
							        	it.remove(); 
							    	}
							    }
							}
						}
					}
				});
		// Create the AlertDialog object and return it
		return builder.create();
	}
}
