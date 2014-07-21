package com.voidestate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;


public class MainActivity extends Activity {
	 
    // Google Map
    private GoogleMap googleMap;
    
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
     List<Map<String, String>> employeeList = new ArrayList<Map<String, String>>();
    
     try {
      JSONObject jsonResponse = new JSONObject(jsonResult);
      JSONArray jsonMainNode = jsonResponse.optJSONArray("houses");
    
      for (int i = 0; i < jsonMainNode.length(); i++) {
       JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
       String key_id = jsonChildNode.optString("key_id");
       String province = jsonChildNode.optString("province_name");
       String price = jsonChildNode.optString("price");
       String description = jsonChildNode.optString("description");
       
       String outPut = key_id + "." + province + " " + price + "description :" + description;
       
       employeeList.add(createEmployee("employees", outPut));
      }
     } catch (JSONException e) {
      Toast.makeText(getApplicationContext(), "Error" + e.toString(),
        Toast.LENGTH_SHORT).show();
     }
    
     SimpleAdapter simpleAdapter = new SimpleAdapter(this, employeeList,
       android.R.layout.simple_list_item_1,
       new String[] { "employees" }, new int[] { android.R.id.text1 });
     listView.setAdapter(simpleAdapter);
    }
    
    private HashMap<String, String> createEmployee(String name, String number) {
     HashMap<String, String> employeeNameNo = new HashMap<String, String>();
     employeeNameNo.put(name, number);
     return employeeNameNo;
    }
    
    
    @Override
    protected void onResume() {
        super.onResume();
        //initilizeMap();
    }
 
}
