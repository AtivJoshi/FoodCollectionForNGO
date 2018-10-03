package com.example;

import java.util.ArrayList;
import java.util.Collections;

public class RestaurantSelect {
    private ArrayList<Cluster> clusterArray = new ArrayList<>(); //all clusters
    private ArrayList<Restaurant> selectedRestaurantArray = new ArrayList<>();
    private ArrayList<Cluster> selectedClusterArray = new ArrayList<>();
    private ArrayList<Restaurant> restaurantInCluster;
    private int capacity; //capacity of the NGO
    
    public RestaurantSelect (ArrayList<Cluster> clusterArray, int capacity) 
    {
        this.clusterArray = clusterArray;
        this.capacity = capacity;
    }
    
    public ArrayList<Restaurant> getSelectedRestaurant()
    {
        ClusterSelect clusterSelect = new ClusterSelect(clusterArray, capacity);
        int i,j,qtyRemaining=capacity;

         selectedClusterArray = clusterSelect.getSelectedCluster();

        for(i=0;i<selectedClusterArray.size()-1;i++)
        {
            restaurantInCluster = selectedClusterArray.get(i).getRestaurantArray();
            qtyRemaining = qtyRemaining - selectedClusterArray.get(i).getQty();
            selectedRestaurantArray.addAll(restaurantInCluster);
            
/*
            for(int k = 0; k<restaurantInCluster.size(); k++)
            {
                selectedRestaurantArray.add(restaurantInCluster.get(k));
                System.out.println("inside  :: " +  restaurantInCluster.get(k).getName());
            }
*/
        }

        if(selectedClusterArray.get(i).getQty() >= qtyRemaining)
        {	
            Collections.sort(selectedClusterArray.get(i).getRestaurantArray());
/*
            for(int k = 0; k<selectedClusterArray.get(i).getRestaurantArray().size(); k++)
            {
                System.out.println("inside  :: " +  selectedClusterArray.get(i).getRestaurantArray().get(k).getQty());
            }
*/
            for(j=0;j<selectedClusterArray.get(i).getRestaurantArray().size();j++)
            {
                if(selectedClusterArray.get(i).getRestaurantArray().get(j).getQty()>=qtyRemaining)
                {
                    selectedRestaurantArray.add(selectedClusterArray.get(i).getRestaurantArray().get(j));
                    break;
                }
                else
                {
                    selectedRestaurantArray.add(selectedClusterArray.get(i).getRestaurantArray().get(j));
                    qtyRemaining = qtyRemaining - selectedClusterArray.get(i).getRestaurantArray().get(j).getQty();
                }
            }
        }
        return selectedRestaurantArray;
    }
}
