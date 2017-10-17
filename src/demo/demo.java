package demo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import dbscan.DBSCAN;
import dbscan.DBSCANPoint;

public class demo {
	
	public static void main(String[] args) throws IOException{
		// create test dataset for clustering
		List<demoPoint> data = dataset(100);
		
		System.out.println(" - Running DBSCAN - ");
		DBSCAN dbscan = new DBSCAN((ArrayList<? extends DBSCANPoint>) data, 10, 10);
		ArrayList<ArrayList<DBSCANPoint>> groups  = dbscan.cluster();
		
//		System.out.println(groups);
		System.out.println("FOUND " +groups.size() + " clusters");
		
	}
	
	public static List<demoPoint> dataset(int size){
		List<demoPoint> c1 = cluster(50,50,50,10);
		List<demoPoint> c2 = cluster(150,150,50,10);
		List<demoPoint> c3 = cluster(100,100,50,10);
		
		c1.addAll(c2);
		c1.addAll(c3);
		Collections.shuffle(c1);
		
		return c1;
	}
	
	public static List<demoPoint> cluster(int cx, int cy, int size, int var){
		List<demoPoint> points = new ArrayList<demoPoint>();
		int i = 0;
		while (i < size){
			int x = randInt(cx-var, cx+var+1);
			int y = randInt(cy-var, cx+var+1);
			
			points.add(new demoPoint(x,y));
			i++;
		}
		
		return points;
	}
	
	public static int randInt(int min, int max) {
	    Random rand = ThreadLocalRandom.current();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}

}
