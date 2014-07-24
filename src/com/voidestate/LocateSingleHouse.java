package com.voidestate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocateSingleHouse extends Activity {
	

	private GoogleMap googleMap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locate_single_house);
        
        Intent intent = getIntent();
        double lat = intent.getDoubleExtra("lat", 0);
        double lon = intent.getDoubleExtra("lon", 0);
        
        try {
            // Loading map
            initilizeMap();
            
            GoogleMap map = ((MapFragment) getFragmentManager()
                    .findFragmentById(R.id.map)).getMap();

            map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(lat, lon), (float) 11.00));
            
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            googleMap.addMarker(new MarkerOptions()
                    	.position(new LatLng(lat, lon))
                    	.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


        } catch (Exception e) {
            e.printStackTrace();
        }
        
	}
        
        private void initilizeMap() {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.map)).getMap();
     
                // check if map is created successfully or not
                if (googleMap == null) {
                    Toast.makeText(getApplicationContext(),
                            "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                            .show();
                }
            }
         
        }
        
        @Override
        protected void onResume() {
            super.onResume();
            initilizeMap();
        }

}

