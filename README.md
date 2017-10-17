# DBSCAN #

Java implementation of DBSCAN clustering algorithm

## Paramters ##
### minPts 
- minimum number of points in cluster
### eps
- epsilon value = max distance between points to be considered neighbors
### data
- array of points to cluster

## Run ##
```
DBSCAN dbscan = new DBSCAN((ArrayList<? extends DBSCANPoint>) data, eps, minPts);
ArrayList<ArrayList<DBSCANPoint>> groups  = dbscan.cluster();
```