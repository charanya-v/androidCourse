package com.example.tododemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EditListActivity extends ActionBarActivity {

int position=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_list);
		
		String lineItem = getIntent().getExtras().getString("editText");
		position = getIntent().getExtras().getInt("position");
		EditText editLineItem = (EditText) findViewById(R.id.editItem);
		editLineItem.setText(lineItem);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_list, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void editListItem(View v){
		EditText updatedText = (EditText) findViewById(R.id.editItem);
		Intent intent = new Intent(EditListActivity.this, ToDoListActivity.class);
		intent.putExtra("updatedTxt", updatedText.getText().toString());
		intent.putExtra("postion", position);
		setResult(RESULT_OK,intent);
		finish();


	}

}
