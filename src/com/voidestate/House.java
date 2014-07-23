package com.voidestate;

public class House {
	
	private int id;
	private double lat;
	private double lon;
	private String address;
    private String province_name;
    private String city;
    private String nearest_big_city;
    private double distance_to_nearest_big_city;
    private double distance_to_the_sea;
    private int number_of_bedrooms;
    private int number_of_bathrooms;
    private int floors;
    private boolean yard;
    private double size;
    private String description;
    private double price;
    private String pictures;
	
	public House() {

	}

	public House(int _id, double _lat, double _lon, String _address, String _province_name, String _city, String _nearest_big_city,
				 double _distance_to_nearest_big_city, double _distance_to_the_sea, int _number_of_bedrooms, int _number_of_bathrooms,
				 int _floors, boolean _yard, double _size, String _description, double _price, String _pictures) {
		this.id = _id;
		this.lat = _lat;
		this.lon = _lon;
		this.address = _address;
		this.province_name = _province_name;
		this.city = _city;
		this.nearest_big_city = _nearest_big_city;
		this.distance_to_nearest_big_city = _distance_to_nearest_big_city;
		this.distance_to_the_sea = _distance_to_the_sea;
		this.number_of_bedrooms = _number_of_bedrooms;
		this.number_of_bathrooms = _number_of_bathrooms;
		this.floors = _floors;
		this.yard = _yard;
		this.size = _size;
		this.description = _description;
		this.price = _price;
		this.pictures = _pictures;
	}
	
	public House(double _lat, double _lon, String _address, String _province_name, String _city, String _nearest_big_city,
				 double _distance_to_nearest_big_city, double _distance_to_the_sea, int _number_of_bedrooms, int _number_of_bathrooms,
				 int _floors, boolean _yard, double _size, String _description, double _price, String _pictures) {
		this.lat = _lat;
		this.lon = _lon;
		this.address = _address;
		this.province_name = _province_name;
		this.city = _city;
		this.nearest_big_city = _nearest_big_city;
		this.distance_to_nearest_big_city = _distance_to_nearest_big_city;
		this.distance_to_the_sea = _distance_to_the_sea;
		this.number_of_bedrooms = _number_of_bedrooms;
		this.number_of_bathrooms = _number_of_bathrooms;
		this.floors = _floors;
		this.yard = _yard;
		this.size = _size;
		this.description = _description;
		this.price = _price;
		this.pictures = _pictures;
	}

	public int getId() {
		return this.id;
	}
	
	public void setId(int _id) {
		this.id = _id;
	}
	
	public double getLat() {
		return this.lat;
	}
	
	public void setLat(double _lat) {
		this.lat = _lat;
	}
	
	public double getLon() {
		return this.lon;
	}
	
	public void setLon(double _lon) {
		this.lon = _lon;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String _address) {
		this.address = _address;
	}	
	
	public String getProvinceName() {
		return this.province_name;
	}
	
	public void setProvinceName(String _province_name) {
		this.province_name = _province_name;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public void setCity(String _city) {
		this.city = _city;
	}

	public String getNearestBigCity() {
		return this.nearest_big_city;
	}
	
	public void setNearestBigCity(String _nearest_big_city) {
		this.nearest_big_city = _nearest_big_city;
	}

	public double getDistanceToNearestBigCity() {
		return this.distance_to_nearest_big_city;
	}

	public void setDistanceToNearestBigCity(double _distance_to_nearest_big_city) {
		this.distance_to_nearest_big_city = _distance_to_nearest_big_city;
	}

	public double getDistanceToTheSea() {
		return this.distance_to_the_sea;
	}

	public void setDistanceToTheSea(double _distance_to_the_sea) {
		this.distance_to_the_sea = _distance_to_the_sea;
	}

	public int getNumberOfBedrooms() {
		return this.number_of_bedrooms;
	}

	public void setNumberOfBedrooms(int _number_of_bedrooms) {
		this.number_of_bedrooms = _number_of_bedrooms;
	}

	public int getNumberOfBathrooms() {
		return this.number_of_bathrooms;
	}

	public void setNumberOfBathrooms(int _number_of_bathrooms) {
		this.number_of_bathrooms = _number_of_bathrooms;
	}
	
	public int getFloors() {
		return this.floors;
	}

	public void setFloors(int _floors) {
		this.floors= _floors;
	}

	public boolean getYard() {
		return this.yard;
	}

	public void setYard(boolean _yard) {
		this.yard = _yard;
	}

	public double getSize() {
		return this.size;
	}

	public void setSize(double _size) {
		this.size = _size;
	}

    public String getDescription() {
    	return this.description;
    }

    public void setDescription(String _description) {
    	this.description = _description;
    }

    public double getPrice() {
    	return this.price;
    }

    public void setPrice(double _price){
    	this.price = _price;
    }
    
    public String getPictures() {
    	return this.pictures;
    }
    
    public void setPictures(String _pictures) {
    	this.pictures = _pictures;
    }
}

