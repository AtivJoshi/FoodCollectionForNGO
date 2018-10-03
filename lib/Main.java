package com.example;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class Main {

	public static double latitude=23.037454;
    public static double longitude=72.552238;
    public static int capacity = 55;

    public static void main(String[] args)
	{
		//array to store restaurants
		ArrayList<Cluster> clusterArrayList;
        ArrayList<com.example.Restaurant> selectedRestaurant;
        HashMap<Integer,Cluster> clusterHashMap = new HashMap<>();
		try
		{
			//reading json from file
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

			//extracting JSONObject from json array and creating an object of Restaurant and store in restaurantArrayList
			for (int i=0;i<jsonArray.length();i++)
			{
				JSONObject o = jsonArray.getJSONObject(i);
//				System.out.println(o.getString("name"));
//				restaurantArrayList.add(new Restaurant(o.getString("name"),o.getDouble("latitude"),o.getDouble("longitude"),o.getInt("qty")));

                if(clusterHashMap.containsKey(o.getInt("cluster")))
                {
                    clusterHashMap.get(o.getInt("cluster")).add(new Restaurant(o.getString("name"),o.getDouble("latitude"),o.getDouble("longitude"),o.getInt("qty")));
                }

                else
                {
/*                    double x = (o.getDouble("longitude") - longitude)*Math.cos((latitude+o.getDouble("latitude"))/2);
                    double y = o.getDouble("latitude")-latitude;
                    double distSq = 6371*Math.sqrt(x*x + y*y);*/
                    clusterHashMap.put(o.getInt("cluster"),new Cluster(new Restaurant(o.getString("name"),o.getDouble("latitude"),o.getDouble("longitude"),o.getInt("qty")),distance(latitude,longitude,o.getDouble("latitude"),o.getDouble("longitude"))));

                }
			}

            clusterArrayList = new ArrayList<>(clusterHashMap.values());
            for(int i=0; i<clusterArrayList.size(); i++)
            {
                System.out.println(clusterArrayList.get(i).getRestaurantArray().size());
                System.out.println(clusterArrayList.get(i).getRatio());
            }
            RestaurantSelect rs = new RestaurantSelect(clusterArrayList, capacity);
            selectedRestaurant = rs.getSelectedRestaurant();

            for(int i=0; i<selectedRestaurant.size(); i++)
            {
                System.out.println(selectedRestaurant.get(i).getName());
            }


		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

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