package com.example;


import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author pratik
 */
public class ClusterSelect {
    private ArrayList<Cluster> clusterArray = new ArrayList<Cluster>(); //all clusters
    private ArrayList<Cluster> selectedClusterArray = new ArrayList<Cluster>(); //selected cluster using fractional knapsack
    private final double thresholdRatio = 0.001; //fixed value
    private int capacity; //capacity of the NGO
    
    //constructor
    public ClusterSelect(ArrayList<Cluster> clusterArray, int capacity) 
    {
        this.clusterArray = clusterArray;
        this.capacity = capacity;
    }

    //sorts all the clusters on the basis of the ratio
    public void sortCluster()
    {
        Collections.sort(clusterArray);
    }
    
    public void displayCluster()
    {
        for(int i = 0; i<clusterArray.size(); i++) {
            System.out.println(clusterArray.get(i).getQty());
            System.out.println(clusterArray.get(i).getRatio());
        }
    }
    
    //fractional knapsack
    public ArrayList<Cluster> getSelectedCluster()
    {
        int quantityCollected = 0;
        this.sortCluster();
//        this.displayCluster();

        //completely select these clusters
        while(true)
        {
            int clusterQty = clusterArray.get(0).getQty();
            if(quantityCollected + clusterQty <= capacity)
            {
                quantityCollected += clusterQty; //quantity is added
                selectedClusterArray.add(clusterArray.get(0)); //cluster 
                clusterArray.remove(0);
                if(clusterArray.size()==0)
                    break;
            }
            else
            {
                break;
            }
        }
        
        int requirement = capacity - quantityCollected;
//        System.out.println("req :: "+requirement);
//        System.out.println("cluster size:: "+clusterArray.size());
        
        for(int i = 0; i < clusterArray.size(); i++)
        {
            clusterArray.get(i).setQty(Math.min(clusterArray.get(i).getQty(), requirement));
            clusterArray.get(i).calcRatio();
//            System.out.println("ratio:: "+clusterArray.get(i).getRatio());
        }
    
        this.sortCluster();

        while(quantityCollected<capacity && clusterArray.size()>0 && clusterArray.get(0).getRatio()>=thresholdRatio)
        {
            quantityCollected += clusterArray.get(0).getQty();
            selectedClusterArray.add(clusterArray.get(0));
            clusterArray.remove(0);
        }
//        System.out.println("cluster size:: "+clusterArray.size());
//        System.out.println("collected :: "+quantityCollected);
        
        return selectedClusterArray;
    }
}
