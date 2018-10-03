package com.example.lib_cluster;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MyClass {

    public static void main(String... args)
    {
        int cluster = 1;
        int threshold=300;
        ArrayList<com.example.lib_cluster.Restaurant> restaurantArrayList = new ArrayList<>();
        ArrayList<Cluster> clusterArrayList = new ArrayList<>();
        try
        {
            InputStream inputStream = new FileInputStream("/home/ativ/NGOLocator/lib/src/main/java/Restaurant.json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;

            //building json string
            while((line = bufferedReader.readLine())!= null)
            {
                sb.append(line);
            }

            //converitng string to json array
            JSONArray jsonArray = new JSONArray(sb.toString());

            for (int i=0;i<jsonArray.length();i++)
            {
                JSONObject o = jsonArray.getJSONObject(i);
                restaurantArrayList.add(new Restaurant(o.getString("name"),o.getDouble("latitude"),o.getDouble("longitude"),o.getInt("qty")));
            }

/*            for (Restaurant r :
                    restaurantArrayList) {
                System.out.println(r.getName());
            }*/

            for(int i=0;i<restaurantArrayList.size();i++)
            {
                if(restaurantArrayList.get(i)!=null)
                {
                    Cluster c = new Cluster();
                    c.add(restaurantArrayList.get(i));
                    for(int j=i+1;j<restaurantArrayList.size();j++)
                    {
                        if(restaurantArrayList.get(j)!=null)
                        {
                            System.out.println(restaurantArrayList.get(i).getName() + " " + restaurantArrayList.get(j).getName());
                            System.out.println(distance(restaurantArrayList.get(i).getLongitude(), restaurantArrayList.get(i).getLatitude(), restaurantArrayList.get(j).getLongitude(), restaurantArrayList.get(j).getLatitude()));
                            if (distance(restaurantArrayList.get(i).getLongitude(), restaurantArrayList.get(i).getLatitude(), restaurantArrayList.get(j).getLongitude(), restaurantArrayList.get(j).getLatitude()) < 500 )
                            {
                                c.add(restaurantArrayList.get(j));
                                restaurantArrayList.set(j,null);
                            }
                        }
                    }
                    clusterArrayList.add(c);
                }
            }

            System.out.println("Selected");
/*
            while(restaurantArrayList.size()!=0)
            {
                Cluster cluster1 = new Cluster();
                for(int i=1;i<restaurantArrayList.size()+1;i++)
                {
//                    System.out.println();
                    System.out.println(distance(restaurantArrayList.get(0).getLongitude(),restaurantArrayList.get(0).getLatitude(),restaurantArrayList.get(i).getLongitude(),restaurantArrayList.get(i).getLatitude()));
                    if(distance(restaurantArrayList.get(0).getLongitude(),restaurantArrayList.get(0).getLatitude(),restaurantArrayList.get(i).getLongitude(),restaurantArrayList.get(i).getLatitude())<300)
                    {
                        cluster1.add(restaurantArrayList.get(i));
                        i--;
                    }
                }

                clusterArrayList.add(cluster1);
            }
*/
            for (Cluster c :
                    clusterArrayList) {
                c.print();
                System.out.println();
            }

/*            for(int i=0;i<clusterArrayList.size();i++)
            {

            }*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
    public static double distance(double lamda1, double phi1, double lamda2, double phi2)
    {
        double x= (lamda1 - lamda2)*Math.cos(((phi1+phi2)/360)*Math.PI);
        double y= phi1-phi2;
        return 6371*Math.sqrt(x*x + y*y);
    }*/
    public static double toRadians (double val)
    {
        double inRadian;
        inRadian = val * Math.PI/180;
        return inRadian;
    }

    public static double distance(double lat1, double long1, double lat2, double long2)
    {
        double R = 6371000; // metres

        double phi1 = toRadians(lat1);
        double phi2 = toRadians(lat2);
        double deltaphi = toRadians((lat2-lat1));
        double deltalamda = toRadians((long2-long1));

        double a = Math.sin(deltaphi/2) * Math.sin(deltaphi/2) +
                Math.cos(phi1) * Math.cos(phi2) *
                        Math.sin(deltalamda/2) * Math.sin(deltalamda/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double d = R * c;

        return d;
    }
}
