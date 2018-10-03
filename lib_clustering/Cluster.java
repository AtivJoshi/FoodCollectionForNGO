package com.example.lib_cluster;

import java.util.ArrayList;

/**
 *
 * @author pratik
 */

public class Cluster implements Comparable<Cluster> {

    private ArrayList<Restaurant> restaurantArray = new ArrayList<Restaurant>();
    private double ratio;
    private double dist;
    private int qty;

    public void print()
    {
        for (Restaurant r:restaurantArray) {
            System.out.println(r.getName());
        }
    }


    public void add(Restaurant r)
    {
        restaurantArray.add(r);
//        dist+=r.getDistance();
        qty+=r.getQty();
        this.calcRatio();
    }

    public Cluster(Restaurant r, double dist)
    {
        this.dist=dist;
        qty=0;
        add(r);
    }

    public Cluster()
    {qty=0;}


    public ArrayList<Restaurant> getRestaurantArray() {
        return restaurantArray;
    }

    public double getRatio() {
//        ratio=(qty *restaurantArray.size())/dist;
        return ratio;
    }

    public void calcRatio() {
        ratio = qty/dist;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
//        System.out.println("ngo_locator.Cluster.setRatio()" + this.ratio);
    }

    @Override
    public int compareTo(Cluster o) {
        if(this.ratio < o.ratio)
            return 1;
        else
            return -1;
    }

}