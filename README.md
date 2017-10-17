# DBSCAN #

Java implementation of DBSCAN clustering algorithm

```

List<myPoint> data = // create some data set;
int minPts = 10; // min points in cluster
double eps = 10; // epsilon value = max distance between points to be considered neighbors
DBSCAN dbscan = new DBSCAN((ArrayList<? extends DBSCANPoint>) data, eps, minPts);
ArrayList<ArrayList<DBSCANPoint>> groups  = dbscan.cluster();
```