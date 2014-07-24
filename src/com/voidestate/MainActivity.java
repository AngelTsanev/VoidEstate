package com.voidestate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends Activity {
	 
    // Google Map
    private GoogleMap googleMap;
    private String jsonResult;
    private String url = "http://46.238.17.97:8000/sqlrequest/myFile.php";
   
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
                    new LatLng(42.456, 25.542), (float) 7.25));

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        accessWebService();
    }
    
    
    //---------------------------------
    // Async Task to access the web
    private class JsonReadTask extends AsyncTask<String, Void, String> {
     @Override
     protected String doInBackground(String... params) {
      HttpClient httpclient = new DefaultHttpClient();
      HttpPost rate = new HttpPost(params[0]);
      try {
       HttpResponse response = httpclient.execute(rate);
       jsonResult = inputStreamToString(
         response.getEntity().getContent()).toString();
      }
    
      catch (ClientProtocolException e) {
       e.printStackTrace();
      } catch (IOException e) {
       e.printStackTrace();
      }
      return null;
     }
    
     private StringBuilder inputStreamToString(InputStream is) {
      String rLine = "";
      StringBuilder answer = new StringBuilder();
      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
    
      try {
       while ((rLine = rd.readLine()) != null) {
        answer.append(rLine);
       }
      }
    
      catch (IOException e) {
       // e.printStackTrace();
       Toast.makeText(getApplicationContext(),
         "Error..." + e.toString(), Toast.LENGTH_LONG).show();
      }
      return answer;
     }
    
     @Override
     protected void onPostExecute(String result) {
      MarkerDrawer();
     }
    }// end async task
    
    public void accessWebService() {
     JsonReadTask task = new JsonReadTask();
     // passes values for the urls string array
     task.execute(new String[] { url });
    }
    
    // build hash set for list view
    public void MarkerDrawer() {
     int key;
     double lat;
     double lon;
     String address;
     
    try {
        JSONObject jsonResponse = new JSONObject(jsonResult);
        final JSONArray jsonMainNode = jsonResponse.optJSONArray("houses");
        
    
      for (int i = 0; i < jsonMainNode.length(); i++) {
       JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
       
       key = jsonChildNode.optInt("key_id");
       lat = jsonChildNode.optDouble("latitude");
       lon = jsonChildNode.optDouble("longitude");
       address = jsonChildNode.optString("address");

       googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
       googleMap.addMarker(new MarkerOptions()
               	.position(new LatLng(Double.valueOf(lat), Double.valueOf(lon)))
               	.snippet(address)
               	.title("Id : " + key));      
      }

      googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener()
      {
    	  @Override
    	  public void onInfoWindowClick(Marker mark) {
        	  String[] splitted = mark.getTitle().split("\\s+");
              int key = Integer.parseInt(splitted[2]); // if marker source is clicked
              
              JSONObject jsonChildNode;
			try {
				  jsonChildNode = jsonMainNode.getJSONObject(key-1);
				  Intent intent = new Intent (MainActivity.this, SingleHouse.class);
	              
	              intent.putExtra("key_id", jsonChildNode.optInt("key_id"));
	              intent.putExtra("latitude", jsonChildNode.optDouble("latitude"));
	              intent.putExtra("longitude", jsonChildNode.optDouble("longitude"));
	              intent.putExtra("address", jsonChildNode.optString("address"));
	              intent.putExtra("province_name",jsonChildNode.optString("province_name"));
	              intent.putExtra("city", jsonChildNode.optString("city"));
	              intent.putExtra("nearest_city", jsonChildNode.optString("nearest_city"));
	              intent.putExtra("distance_to_near_city", jsonChildNode.optInt("distance_to_near_city"));
	              intent.putExtra("distance_to_sea", jsonChildNode.optInt("distance_to_sea"));
	              intent.putExtra("bedrooms", jsonChildNode.optInt("bedrooms"));
	              intent.putExtra("bathrooms", jsonChildNode.optInt("bathrooms"));
	              intent.putExtra("floors", jsonChildNode.optInt("floors"));
	              intent.putExtra("yard", jsonChildNode.optBoolean("yard"));
	              intent.putExtra("size", jsonChildNode.optDouble("size"));
	              intent.putExtra("description", jsonChildNode.optString("description"));
	              intent.putExtra("price", jsonChildNode.optDouble("price"));
	             
	              startActivity(intent);                   
	              
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

          }

		


      });
    
     } catch (JSONException e) {
      Toast.makeText(getApplicationContext(), "Error" + e.toString(),
        Toast.LENGTH_SHORT).show();
     }    
     
    }
    
    //---------------------------------
     
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
       }
 
}
