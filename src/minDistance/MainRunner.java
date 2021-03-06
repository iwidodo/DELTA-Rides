package minDistance;

import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JFrame;

import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
public class MainRunner {

	
	
	public static void main(String[] args) throws MalformedURLException{
		
		// ****************************************************************************************
		// READ INPUT DATA, INITIATE OBJECTS 
		// ****************************************************************************************
		
		// create new location object
		Locations locs = new Locations();
		
	// UNCOMMENT to Select Desired Input File **************************************
		// locs.readLocInfo("2_locations_0_cap.txt");
		// locs.readLocInfo("10_locations_2_vol");
		// locs.readLocInfo("9_locations_3_vol");
		// locs.readLocInfo("7_locations_1_vol");
		// locs.readLocInfo("20_locations_4_vol");
		// locs.readLocInfo("15_locations_3_vol");
		// locs.readLocInfo("14_locations_2_vol");
		// locs.readLocInfo("12_locations_6_vol");
		// locs.readLocInfo("16_locations_4_vol");
		// locs.readLocInfo("35_locations_5_vol");
		// locs.readLocInfo("28_locations_7_vol");
		// locs.readLocInfo("10_locations_5_vol");
		// locs.readLocInfo("32_locations_8_vol");
		// locs.readLocInfo("15_locations_5_vol");
		// locs.readLocInfo("5_locations_2_vol");
		// locs.readLocInfo("13_locations_3_vol");
		locs.readLocInfo("25_locations_4_vol");
		// locs.readLocInfo("9_locations_2_vol");
		// locs.readLocInfo("10_locations_4_vol");
		// locs.readLocInfo("19_locations_6_vol");
		
		//input from website - IGNORE, WEBSITE IN DEV
		//locs.readLocInfo("attendees.txt");
		
		
		// if there is not enough capacity, then add school busses
		locs.addBus();
		// calculate distance between any two location pairs
		locs.calculateDistanceMatrix();
		
		// below code will show output as a visual graph 
		Visualization vis = new Visualization(locs, "Locations of drivers, passengers and destination");
		vis.showNodes();
		vis.setSize(vis.mapXsize, vis.mapYsize);
		vis.setVisible(true);
			
		
		
		// ****************************************************************************************
		// CLUSTER PASSENGERS FOR EACH DRIVER 
		// ****************************************************************************************				
			
		Clustering cls = new Clustering(locs);
		cls.assignToClosestDriver();
		
		
		// below code will show output as a visual graph
		Visualization vis2 = new Visualization(locs, "Clustering by assigning to closest driver");
		vis2.showClustering();
		vis2.setSize(vis2.mapXsize, vis2.mapYsize);
		vis2.setVisible(true);
		
		
		// ****************************************************************************************
		// FIND OPTIMAL PICKUP ROUTES FOR EACH DRIVER  
		// ****************************************************************************************	
		
		// run the greedy heuristic algorithm for each cluster and between drivers
		
		  GRASPforClustering gc = new GRASPforClustering(locs);
		  gc.searchDrivers();
	   
		
		// below code will show output as a visual graph and google maps through launching web browser with path URL
		Visualization vis3 = new Visualization(locs, "Optimal routes for each driver");
		vis3.showRoute();
		vis3.setSize(vis3.mapXsize, vis3.mapYsize);
		vis3.setVisible(true);

	}

}
