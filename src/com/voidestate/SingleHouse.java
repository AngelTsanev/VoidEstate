package com.voidestate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SingleHouse extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_house);
		
		Intent intent = getIntent();
		House house = new House();
		
		house.setId(intent.getIntExtra("key_id", 0));
	    house.setLat(intent.getDoubleExtra("latitude", 0));
	    house.setLon(intent.getDoubleExtra("longitude", 0));
	    house.setAddress(intent.getStringExtra("address"));
	    house.setProvinceName(intent.getStringExtra("province_name"));
	    house.setCity(intent.getStringExtra("city"));
	    house.setNearestBigCity(intent.getStringExtra("nearest_city"));
	    house.setDistanceToNearestBigCity(intent.getDoubleExtra("distance_to_near_city", 0));
	    house.setDistanceToTheSea(intent.getDoubleExtra("distance_to_sea", 0));
	    house.setNumberOfBedrooms(intent.getIntExtra("bedrooms", 0));
	    house.setNumberOfBathrooms(intent.getIntExtra("bathrooms", 0));
	    house.setFloors(intent.getIntExtra("floors", 0));
	    house.setYard(intent.getBooleanExtra("yard", false));
	    house.setSize(intent.getDoubleExtra("size", 0));
	    house.setDescription(intent.getStringExtra("description"));
	    house.setPrice(intent.getDoubleExtra("price", 0));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single_house, menu);
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
}
