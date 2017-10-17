package dbscan;

import java.util.ArrayList;

public class DBSCAN {
	
	private static double eps = 0.5;
    private static int minDBSCANPoints = 1;
    private double[][] matrix;
    private ArrayList<DBSCANPoint> DBSCANPoints;
    private int numDBSCANPoints = 0;
    
	public DBSCAN(ArrayList<? extends DBSCANPoint> DBSCANPoints, double eps, int minDBSCANPoints){
		this.DBSCANPoints = (ArrayList<DBSCANPoint>) DBSCANPoints;
		numDBSCANPoints = DBSCANPoints.size();
		this.eps = eps;
		this.minDBSCANPoints = minDBSCANPoints;
		
	}
	
	public DBSCAN setMinDBSCANPoints(int min){
		minDBSCANPoints = min;
		return this;
	}
	
	public DBSCAN setEps(double eps){
		this.eps = eps;
		return this;
	}
	
	public ArrayList<ArrayList<DBSCANPoint>> cluster(){ 
		
		// calculate distance matrix
        matrix = new double[numDBSCANPoints][numDBSCANPoints];
        for (int i=0; i<numDBSCANPoints; i++) {
        	DBSCANPoint p = DBSCANPoints.get(i);
        	p.setIndex(i);
            for (int j=i+1; j<numDBSCANPoints; j++) {
                matrix[i][j] = p.DBSCANDistance(DBSCANPoints.get(j));
                matrix[j][i] = matrix[i][j];
            }
        }
 
        // prepare clusters
        ArrayList<ArrayList<DBSCANPoint>> clusters = new ArrayList<>();
        for (int i=0; i<numDBSCANPoints; i++) {
        	DBSCANPoint p = DBSCANPoints.get(i);
            if (!p.isVisited()) {
                p.setVisited();
                ArrayList<DBSCANPoint> neighborDBSCANPoints = regionQuery(p, eps);
                if (neighborDBSCANPoints.size() >= minDBSCANPoints) {
                   ArrayList<DBSCANPoint> cluster = new ArrayList<>();
                   expandCluster(p, neighborDBSCANPoints, cluster, eps, minDBSCANPoints);
                   clusters.add(cluster);
                }
            }            
        }
        
        return clusters;
	}
	
	/**
	 * get indexes of all DBSCANPoints around passed index within eps (meters) distance
	 * @param p
	 * @param eps
	 * @return
	 */
	public ArrayList<DBSCANPoint> regionQuery(DBSCANPoint p, double eps) {
        ArrayList<DBSCANPoint> nbrDBSCANPoints = new ArrayList<>();
        int idx = p.getIndex();
        for (int j = 0; j <numDBSCANPoints; j++) {
            if (matrix[idx][j] < eps) {
                nbrDBSCANPoints.add(DBSCANPoints.get(j));
            }
        }
        return nbrDBSCANPoints;
    }
    
    // add to cluster all DBSCANPoints reachable from passed DBSCANPoint p
    public void expandCluster(DBSCANPoint DBSCANPoint, ArrayList<DBSCANPoint>neighborDBSCANPoints, ArrayList<DBSCANPoint>cluster, double eps, int minDBSCANPoints) {
        cluster.add(DBSCANPoint);
        DBSCANPoint.setMember();
        for (int idx=0; idx <neighborDBSCANPoints.size(); idx++) {
        	DBSCANPoint p = neighborDBSCANPoints.get(idx);
            if (!p.isVisited()) {
                p.setVisited();
                ArrayList<DBSCANPoint> nbrDBSCANPoints = regionQuery(p, eps);
                if (nbrDBSCANPoints.size() >= minDBSCANPoints) {
                    neighborDBSCANPoints.addAll(nbrDBSCANPoints);                    
                }
            }
            if (!p.isMember()) {
                cluster.add(p);
                p.setMember();
            }
        }
    }
}