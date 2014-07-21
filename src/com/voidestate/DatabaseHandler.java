package com.voidestate;

import java.util.List;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "cheapBulgarianHouse";
 
    // Houses table name
    private static final String TABLE_HOUSES = "Houses";
 
    // Houses Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_LAT = "lat";
    private static final String KEY_LON = "lon";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PROVINCE_NAME = "province name";
    private static final String KEY_CITY = "city";
    private static final String KEY_NEAREST_BIG_CITY = "nearest big city";
    private static final String KEY_DISTANCE_TO_NEAREST_BIG_CITY = "distance to nearest big city";
    private static final String KEY_DISTANCE_TO_THE_SEA = "distance to the sea";
    private static final String KEY_NUMBER_OF_BEDROOMS = "number of bedrooms";
    private static final String KEY_NUMBER_OF_BATHROOMS = "number of bathrooms";
    private static final String KEY_FLOORS = "floors";
    private static final String KEY_YARD = "yard";
    private static final String KEY_SIZE = "size";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PRICE = "price";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_HOUSES_TABLE = "CREATE TABLE " + TABLE_HOUSES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LAT + " DOUBLE LATITUDE," + KEY_LON + " DOUBLE LONGITUDE," + KEY_ADDRESS + " TEXT," 
        		+ KEY_PROVINCE_NAME + " TEXT," + KEY_CITY + " TEXT" + KEY_NEAREST_BIG_CITY + " TEXT"
        		+ KEY_DISTANCE_TO_NEAREST_BIG_CITY + " DOUBLE DISTANCE TO NEAREST BIG CITY"
        		+ KEY_DISTANCE_TO_THE_SEA + " DOUBLE DISTANCE TO THE SEA" + KEY_NUMBER_OF_BEDROOMS + " INTEGER NUMBER OF BEDROOMS"
        		+ KEY_NUMBER_OF_BATHROOMS + " INTEGER NUMBER OF BATHROOMS" + KEY_FLOORS + " INTEGER FLOORS" + KEY_YARD + " BOOLEAN YARD"
        		+ KEY_SIZE + " DOUBLE SIZE" + KEY_DESCRIPTION + " TEXT" + KEY_PRICE + " DOUBLE PRICE" +")";
        db.execSQL(CREATE_HOUSES_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOUSES);
 
        // Create tables again
        onCreate(db);
    }
    
    // Adding new house
    public void addHouse(House house) {
    	
    	SQLiteDatabase db = this.getWritableDatabase();
 
    	ContentValues values = new ContentValues();
    	values.put(KEY_LAT, house.getLat()); // House Latitude
    	values.put(KEY_LON, house.getLon()); // House Longitude
    	values.put(KEY_ADDRESS, house.getAddress()); // House Address
    	values.put(KEY_PROVINCE_NAME, house.getProvinceName()); // House Province Name
    	values.put(KEY_CITY, house.getCity()); // House City
    	values.put(KEY_NEAREST_BIG_CITY, house.getNearestBigCity()); // Nearest big city to the house
    	values.put(KEY_DISTANCE_TO_NEAREST_BIG_CITY, house.getDistanceToNearestBigCity()); // Distance to the nearest big city
    	values.put(KEY_DISTANCE_TO_THE_SEA, house.getDistanceToTheSea()); // Distance to the sea
    	values.put(KEY_NUMBER_OF_BEDROOMS, house.getNumberOfBedrooms()); // Number of bedrooms
    	values.put(KEY_NUMBER_OF_BATHROOMS, house.getNumberOfBathrooms()); // Number of bathrooms
    	values.put(KEY_FLOORS, house.getFloors()); // Number of floors
    	values.put(KEY_YARD, house.getYard()); // Yard
    	values.put(KEY_SIZE, house.getSize()); // Size of the house
    	values.put(KEY_DESCRIPTION, house.getDescription()); // Description of the house
    	values.put(KEY_PRICE, house.getPrice()); // Price of the house
 
    	// Inserting Row
    	db.insert(TABLE_HOUSES, null, values);
    	db.close(); // Closing database connection
    	
    }
    
    // Getting single house
	public House getHouse(int id) {
	    SQLiteDatabase db = this.getReadableDatabase();
	 
	    Cursor cursor = db.query(TABLE_HOUSES, new String[] { KEY_ID,
	           KEY_LAT, KEY_LON, KEY_ADDRESS, KEY_PROVINCE_NAME, KEY_CITY, KEY_NEAREST_BIG_CITY, KEY_DISTANCE_TO_NEAREST_BIG_CITY,
	           KEY_DISTANCE_TO_THE_SEA, KEY_NUMBER_OF_BEDROOMS, KEY_NUMBER_OF_BATHROOMS, KEY_FLOORS, KEY_YARD, KEY_SIZE,
	           KEY_DESCRIPTION, KEY_PRICE }, KEY_ID + "=?",
	            new String[] { String.valueOf(id) }, null, null, null, null);
	    if (cursor != null)
	        cursor.moveToFirst();
	 	
	    House house = new House(Integer.parseInt(cursor.getString(0)),
	            Double.parseDouble(cursor.getString(1)), Double.parseDouble(cursor.getString(2)), cursor.getString(3), 
	            cursor.getString(4), cursor.getString(5), cursor.getString(6), Double.parseDouble(cursor.getString(7)),
				Double.parseDouble(cursor.getString(8)), Integer.parseInt(cursor.getString(9)),
				Integer.parseInt(cursor.getString(10)), Integer.parseInt(cursor.getString(11)),
				Integer.parseInt(cursor.getString(12)), Double.parseDouble(cursor.getString(13)),
				cursor.getString(14), Double.parseDouble(cursor.getString(15)));
	    // return house
	    return house;
	}
    
	// Getting All Houses
	public List<House> getAllHouses() {
	    List<House> housesList = new ArrayList<House>();
	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + TABLE_HOUSES;
	 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            House house = new House();
	            house.setId(Integer.parseInt(cursor.getString(0)));
	            house.setLat(Double.parseDouble(cursor.getString(1)));
	            house.setLon(Double.parseDouble(cursor.getString(2)));
	            house.setAddress(cursor.getString(3));
	            house.setProvinceName(cursor.getString(4));
	            house.setCity(cursor.getString(5));
	            house.setNearestBigCity(cursor.getString(6));
	            house.setDistanceToNearestBigCity(Double.parseDouble(cursor.getString(7)));
	            house.setDistanceToTheSea(Double.parseDouble(cursor.getString(8)));
	            house.setNumberOfBedrooms(Integer.parseInt(cursor.getString(9)));
	            house.setNumberOfBathrooms(Integer.parseInt(cursor.getString(10)));
	            house.setFloors(Integer.parseInt(cursor.getString(11)));
	            house.setYard(Integer.parseInt(cursor.getString(12)));
	            house.setSize(Double.parseDouble(cursor.getString(13)));
	            house.setDescription(cursor.getString(14));
	            house.setPrice(Double.parseDouble(cursor.getString(15)));


	            // Adding house to list
	            housesList.add(house);
	        } while (cursor.moveToNext());
	    }
	 
	    // return houses list
	    return housesList;
	}

	// Getting houses count
	public int getHousesCount() {
	    String countQuery = "SELECT  * FROM " + TABLE_HOUSES;
	    SQLiteDatabase db = this.getReadableDatabase();
	    Cursor cursor = db.rawQuery(countQuery, null);
	    cursor.close();
	
	    // return count
	    return cursor.getCount();
	}
	 
	// Updating single house
	public int updateHouse(House house) {
	    SQLiteDatabase db = this.getWritableDatabase();
	     
	    ContentValues values = new ContentValues();
	    values.put(KEY_ID, house.getId()); // House ID
	    values.put(KEY_LAT, house.getLat()); // House Latitude
    	values.put(KEY_LON, house.getLon()); // House Longitude
    	values.put(KEY_ADDRESS, house.getAddress()); // House Address
    	values.put(KEY_PROVINCE_NAME, house.getProvinceName()); // House Province Name
    	values.put(KEY_CITY, house.getCity()); // House City
    	values.put(KEY_NEAREST_BIG_CITY, house.getNearestBigCity()); // Nearest big city to the house
    	values.put(KEY_DISTANCE_TO_NEAREST_BIG_CITY, house.getDistanceToNearestBigCity()); // Distance to the nearest big city
    	values.put(KEY_DISTANCE_TO_THE_SEA, house.getDistanceToTheSea()); // Distance to the sea
    	values.put(KEY_NUMBER_OF_BEDROOMS, house.getNumberOfBedrooms()); // Number of bedrooms
    	values.put(KEY_NUMBER_OF_BATHROOMS, house.getNumberOfBathrooms()); // Number of bathrooms
    	values.put(KEY_FLOORS, house.getFloors()); // Number of floors
    	values.put(KEY_YARD, house.getYard()); // Yard
    	values.put(KEY_SIZE, house.getSize()); // Size of the house
    	values.put(KEY_DESCRIPTION, house.getDescription()); // Description of the house
    	values.put(KEY_PRICE, house.getPrice()); // Price of the house
	        
	     
	    // updating row
	    return db.update(TABLE_HOUSES, values, KEY_ID + " = ?",
	            new String[] { String.valueOf(house.getId()) });
	    }
	    

	 // Deleting single house
	    public void deleteHouse(House house) {
	    SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(TABLE_HOUSES, KEY_ID + " = ?",
	            new String[] { String.valueOf(house.getId()) });
	    db.close();
	}
}
