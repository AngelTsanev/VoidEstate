package com.voidestate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class FilterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filter);
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

	public void backToMainActivity(View v){
		Intent i = new Intent(FilterActivity.this,MainActivity.class);
		startActivity(i);
		finish();
	}
	public void goToListOfHouses(View v){
		Intent i = new Intent(FilterActivity.this,ListOfHouses.class);
		startActivity(i);
		finish();
	}
}
