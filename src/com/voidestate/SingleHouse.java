package com.voidestate;

//import com.sqisland.android.swipe_image_viewer.MainActivity;
//import com.sqisland.android.swipe_image_viewer.R;
//import com.sqisland.android.swipe_image_viewer.MainActivity.ImagePagerAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleHouse extends Activity {
	 ImageView selectedImage;
	 private double lon;
	 private double lat;
    
	@SuppressLint("UseValueOf") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_house);
		
		Intent intent = getIntent();
		House house = new House();
		
		house.setId(intent.getIntExtra("key_id", 0));
		
	    house.setLat(intent.getDoubleExtra("latitude", 0));
	    lat = intent.getDoubleExtra("latitude", 0);
	    house.setLon(intent.getDoubleExtra("longitude", 0));
	    lon = intent.getDoubleExtra("longitude", 0);
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
	    house.setPictures(intent.getStringExtra("image"));

	    TextView price = new TextView(this);
	    price = (TextView) findViewById(R.id.price);
	    price.setText("£" + new Double(house.getPrice()).toString());
	    
	    TextView id = new TextView(this);
	    id = (TextView) findViewById(R.id.id);
	    id.setText("ID: " + new Integer(house.getId()).toString());
	    
	    TextView city = new TextView(this);
	    city = (TextView) findViewById(R.id.city);
	    city.setText("village/town: " + new String(house.getCity()));
	    
	    TextView province = new TextView(this);
	    province = (TextView) findViewById(R.id.province);
	    province.setText("province: " + new String(house.getProvinceName()));

	    TextView size = new TextView(this);
	    size = (TextView) findViewById(R.id.size);
	    size.setText(new Double(house.getSize()).toString() + " sq. m.");
	    
	    TextView bedrooms = new TextView(this);
	    bedrooms = (TextView) findViewById(R.id.bedrooms);
	    bedrooms.setText(new Integer(house.getNumberOfBedrooms()).toString() + " bedrooms");
	    
	    TextView description = new TextView(this);
	    description = (TextView) findViewById(R.id.description);
	    description.setText(house.getDescription());
	    
	    Button show_on_map = new Button(this);
	    show_on_map = (Button) findViewById(R.id.show_on_map);
	    show_on_map.setText("Show on map");
	    
	    Button call = new Button(this);
	    call = (Button) findViewById(R.id.call);
	    call.setText("Call agent");

	    Button email = new Button(this);
	    email = (Button) findViewById(R.id.email);
	    email.setText("Email agent");
	    
	    ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
	    ImagePagerAdapter adapter = new ImagePagerAdapter();
	    viewPager.setAdapter(adapter);
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
	
	 private class ImagePagerAdapter extends PagerAdapter {
	    private int[] mImages = new int[] {
	        R.drawable.photo1,
	        R.drawable.photo2,
	        R.drawable.photo3,
	        R.drawable.photo4
	    };

	    @Override
	    public int getCount() {
	      return mImages.length;
	    }

	    @Override
	    public boolean isViewFromObject(View view, Object object) {
	      return view == ((ImageView) object);
	    }

	    @Override
	    public Object instantiateItem(ViewGroup container, int position) {
	      Context context = SingleHouse.this;
	      ImageView imageView = new ImageView(context);
	      int padding = context.getResources().getDimensionPixelSize(
	          R.dimen.padding_medium);
	      imageView.setPadding(padding, padding, padding, padding);
	      imageView.setScaleType(ImageView.ScaleType.CENTER);
	      imageView.setImageResource(mImages[position]);
	      ((ViewPager) container).addView(imageView, 0);
	      return imageView;
	    }

	    @Override
	    public void destroyItem(ViewGroup container, int position, Object object) {
	      ((ViewPager) container).removeView((ImageView) object);
	    }
	  }
		
	 	public void make_call(View view) {
	 		Intent callIntent = new Intent(Intent.ACTION_CALL);
	 	    callIntent.setData(Uri.parse("tel:123456789"));
	 	    startActivity(callIntent);
	 	}
	 	
	 	public void send_email(View view) {
	 		Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
	 		emailIntent.setData(Uri.parse("mailto:example@bla.com"));
	 		startActivity(emailIntent);
	 	}
	 	
	 	public void showOnMap(View view){
	 		Intent intent = new Intent(SingleHouse.this, LocateSingleHouse.class);
			intent.putExtra("lon", lon);
			intent.putExtra("lat", lat);
			startActivity(intent);
	 		
	 	}
}
