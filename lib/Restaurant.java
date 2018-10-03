package com.example;

/**
 *
 * @author pratik
 */
public class Restaurant implements Comparable<Restaurant>{

	private String name;
	private double latitude;
	private double longitude;
	private double ratio;
	private int qty;
	private double distance;

	public Restaurant(String name, double latitude, double longitude,int qty)
	{
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.qty=qty;

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getRatio() {
		return ratio;
	}
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	@Override
	public int compareTo(Restaurant o) {
		// TODO Auto-generated method stub
		if(this.qty < o.qty)
			return 1;
		else
			return -1;
	}


}