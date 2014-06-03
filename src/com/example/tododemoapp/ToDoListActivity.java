package com.example.tododemoapp;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ToDoListActivity extends ActionBarActivity {

	ArrayList<String> items;
	ArrayAdapter<String> itemsAdapter;
	ListView lvItems;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_to_do_list);
		
		lvItems = (ListView) findViewById(R.id.lvItems);
		items = new ArrayList<String>();
		//readItems();
		itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		lvItems.setAdapter(itemsAdapter);
		
		items.add("First Item");
		items.add("Second Item");
		setUpListViewListener();
		setUpListViewEditListener();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.to_do_list, menu);
		return true;
	}
	
	public void addToDoItem(View v) {
		EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
		itemsAdapter.add(etNewItem.getText().toString());
		etNewItem.setText("");
		//saveItems();
	}
	
	private void setUpListViewListener() {
		lvItems.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				items.remove(position);
				itemsAdapter.notifyDataSetChanged();
				//saveItems();
				return true;
			}
			
		});
	}
	/*
	private void readItems() {
		File dir = getFilesDir();
		File toDoFile = new File(dir, "todo.txt");
		try {
			items = new ArrayList<String>(FileUtils.readLines(toDoFile));
		} catch (IOException e) {
			items = new ArrayList<String>();
			e.printStackTrace();
		}
	}
	
	private void saveItems() {
		File dir = getFilesDir();
		File toDoFile = new File(dir, "todo.txt");
		try {
			FileUtils.writeLines(toDoFile, items);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/
	private void setUpListViewEditListener() {
		lvItems.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String listItem = items.get(position);
				Intent intent = new Intent( ToDoListActivity.this, EditListActivity.class); 
				intent.putExtra("editText",listItem);
				intent.putExtra("position", position);
				startActivityForResult(intent,1);

			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
	    if (requestCode == 1) {
	        if(resultCode == RESULT_OK){
	            String updatedTxt=intent.getStringExtra("updatedTxt");
	            int position=intent.getIntExtra("postion",0);
	            items.set(position, updatedTxt);
	            itemsAdapter.notifyDataSetChanged();
	        }
	    }
	}

}
