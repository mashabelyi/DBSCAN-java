package dbscan;

public interface DBSCANPoint {
	void setIndex(int i);
	int getIndex();
	double DBSCANDistance(DBSCANPoint p);
	Boolean isVisited();
	void setVisited();
	void setMember();
	Boolean isMember();
	void setNoise();
	
}
