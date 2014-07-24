package com.voidestate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class FilterActivity extends Activity {

	private EditText min_price;
	private EditText max_price;
	private EditText bedrooms;
	private EditText distance_to_city;
	private EditText city;
	private EditText size;
	private String query_url = "http://46.238.17.97:8000/sqlrequest/get_houses_by_multiple_conditions.php?";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filter);
		
		min_price = (EditText) findViewById(R.id.price_from);
		max_price = (EditText) findViewById(R.id.price_to);
		bedrooms = (EditText) findViewById(R.id.number_bedrooms);
		distance_to_city = (EditText) findViewById(R.id.Radius);
		city = (EditText) findViewById(R.id.Town_or_City);
		size = (EditText) findViewById(R.id.editText1);

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
		Intent intent = new Intent(FilterActivity.this,MainActivity.class);
		startActivity(intent);
	}
	public void goToListOfHouses(View v){
		
		String mMinPrice = min_price.getText().toString();
		if (!mMinPrice.isEmpty()) {
			mMinPrice = "price_low=" + mMinPrice + "&";
		}
		
		String mMaxPrice = max_price.getText().toString();
		if (!mMaxPrice.isEmpty()) {
			mMaxPrice = "price_high=" + mMaxPrice + "&";
		}
		
		String mBedrooms = bedrooms.getText().toString();
		if (!mBedrooms.isEmpty()) {
			 mBedrooms = "bedrooms=" + mBedrooms + "&";
		}
		
		String mDistanceToCity = distance_to_city.getText().toString();
		if (!mDistanceToCity.isEmpty()) {
			mDistanceToCity = "radius=" + mDistanceToCity + "&";
		}
		
		String mCity = city.getText().toString();
		if (!mCity.isEmpty()) {
			mCity = "nearest_city=" + mCity + "&";
		}
		
		String mSize = size.getText().toString();
		if (!mSize.isEmpty()) {
			mSize = "size=" + mSize + "&";
		}
		
		Intent intent = new Intent(FilterActivity.this,ListOfHouses.class);
		intent.putExtra("url", query_url + mMinPrice + mMaxPrice + mBedrooms + mDistanceToCity + mCity + mSize);
		startActivity(intent);
	}
}
