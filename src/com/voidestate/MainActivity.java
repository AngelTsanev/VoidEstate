package com.voidestate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;


public class MainActivity extends Activity {
	 
    // Google Map
    private GoogleMap googleMap;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        try {
            // Loading map
            initilizeMap();
            
            GoogleMap map = ((MapFragment) getFragmentManager()
                    .findFragmentById(R.id.map)).getMap();

            map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(42.456, 25.142), (float) 7.5));
 
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
    
    public void searchActivityMethod(View v){
        Intent i = new Intent(MainActivity.this,FilterActivity.class);
        startActivity(i);
        finish();
       }
 
}
