package com.voidestate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;


public class MainActivity extends Activity {
	 
    // Google Map
    private GoogleMap googleMap;
    
    private House house = new House();
    private String jsonResult;
    private String url = "http://192.168.0.105/sqlrequest/myFile.php";
    private ListView listView;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        listView = (ListView) findViewById(R.id.listView1);
        accessWebService();
        
        //JSONObject jsonobject;
        //jsonobject = HttpRequest.getJSONfromURL("http://192.168.0.105");    
        
        /*
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
        */
 
    /**
     * function to load map. If map is not created it will create it for you
     
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
     **/
    }
    
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
      ListDrwaer();
     }
    }// end async task
    
    public void accessWebService() {
     JsonReadTask task = new JsonReadTask();
     // passes values for the urls string array
     task.execute(new String[] { url });
    }
    
    // build hash set for list view
    public void ListDrwaer() {
     final List<House> houseList = new ArrayList<House>();
     List<String> houseInfo = new ArrayList<String>();
    
    try {
      JSONObject jsonResponse = new JSONObject(jsonResult);
      JSONArray jsonMainNode = jsonResponse.optJSONArray("houses");
    
      for (int i = 0; i < jsonMainNode.length(); i++) {
       JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
       
       house.setId(jsonChildNode.optInt("key_id"));
       house.setLat(jsonChildNode.optDouble("latitude"));
       house.setLon(jsonChildNode.optDouble("longitude"));
       house.setAddress(jsonChildNode.optString("address"));
       house.setProvinceName(jsonChildNode.optString("province_name"));
       house.setCity(jsonChildNode.optString("city"));
       house.setNearestBigCity(jsonChildNode.optString("nearest_city"));
       house.setDistanceToNearestBigCity(jsonChildNode.optDouble("distance_to_near_city"));
       house.setDistanceToTheSea(jsonChildNode.optDouble("distance_to_sea"));
       house.setNumberOfBedrooms(jsonChildNode.optInt("bedrooms"));
       house.setNumberOfBathrooms(jsonChildNode.optInt("bathrooms"));
       house.setFloors(jsonChildNode.optInt("floors"));
       house.setYard(jsonChildNode.optBoolean("yard"));
       house.setSize(jsonChildNode.optDouble("size"));
       house.setDescription(jsonChildNode.optString("description"));
       house.setPrice(jsonChildNode.optDouble("price"));
       
       String outPut = house.getId() + "." + house.getProvinceName() + " "
    		   		 + house.getPrice() + "description :" + house.getDescription();
       
       houseInfo.add(outPut);
       houseList.add(house);
      }
     } catch (JSONException e) {
      Toast.makeText(getApplicationContext(), "Error" + e.toString(),
        Toast.LENGTH_SHORT).show();
     }
    
    int houseListSize = houseInfo.size();
    String[] houseListInfo = new String[houseListSize];
    
    for(int i = 0; i < houseListSize; i++) {
    	houseListInfo[i] = houseInfo.get(i);
    }
    
     listView = (ListView) findViewById(R.id.listView1);
     ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, houseListInfo);
     listView.setAdapter(mAdapter);
     
     listView.setOnItemClickListener(new OnItemClickListener() {
    	 @Override
    	 public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    		 Intent intent = new Intent (MainActivity.this, SingleHouse.class);
    		 intent.putExtra("key_id", houseList.get(position).getId());
    		 intent.putExtra("latitude", houseList.get(position).getLat());
    		 intent.putExtra("longitude",houseList.get(position).getLon());
    		 intent.putExtra("address", houseList.get(position).getAddress());
    		 intent.putExtra("province_name",houseList.get(position).getProvinceName());
    		 intent.putExtra("city", houseList.get(position).getCity());
    		 intent.putExtra("nearest_city", houseList.get(position).getNearestBigCity());
    		 intent.putExtra("distance_to_near_city", houseList.get(position).getDistanceToNearestBigCity());
    		 intent.putExtra("distance_to_sea", houseList.get(position).getDistanceToTheSea());
    		 intent.putExtra("bedrooms", houseList.get(position).getNumberOfBedrooms());
    		 intent.putExtra("bathrooms", houseList.get(position).getNumberOfBathrooms());
    		 intent.putExtra("floors", houseList.get(position).getFloors());
    		 intent.putExtra("yard", houseList.get(position).getYard());
    		 intent.putExtra("size", houseList.get(position).getSize());
    		 intent.putExtra("description", houseList.get(position).getDescription());
    		 intent.putExtra("price", houseList.get(position).getPrice());
    		 
    		 startActivity(intent);
    		 finish();
    		 
    	 }
     });
    }    
    
    @Override
    protected void onResume() {
        super.onResume();
        //initilizeMap();
    }
 
}
