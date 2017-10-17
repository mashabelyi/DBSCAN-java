package demo;
import dbscan.DBSCANPoint;

public class demoPoint implements DBSCANPoint{
	
	public int x = 0;
	public int y = 0;
	// implements DBSCANPoint
    public int index;
 	private Boolean ismember = false;
 	private Boolean isvisited = false;
 	
	public demoPoint(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		return "("+this.x+", "+this.y+")";
	}

	@Override
	public void setIndex(int i) {
		this.index = i;
		
	}
	@Override
	public int getIndex() {
		return this.index;
	}
	@Override
	public double DBSCANDistance(DBSCANPoint p) {
		demoPoint pp = (demoPoint) p;
		// return euclidean distance
		return Math.sqrt(Math.pow(this.x-pp.x, 2) + Math.pow(this.y-pp.y,2));
	}
	@Override
	public Boolean isVisited() {
		return isvisited;
	}
	@Override
	public void setVisited() {
		isvisited = true;
	}
	@Override
	public void setMember() {
		ismember = true;
	}
	@Override
	public Boolean isMember() {
		return ismember;
	}
	@Override
	public void setNoise() {
		// TODO Auto-generated method stub
		
	}

}
