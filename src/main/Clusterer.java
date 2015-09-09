import java.io.*;
import java.util.*;

public class Clusterer
{
	public static void main(String[] args) throws Exception
	{
		String filename = "train_day_";
		String format = ".csv";
		final int LIMIT =  365;
		final Object EOF = null;
		String fileOut = "train_clstr_15.csv";
		PrintWriter fOut = new PrintWriter(new BufferedWriter(new FileWriter(fileOut,false)));
		
		//ACTIVATE ONE OF THE SETS TO CLASSIFY TRAINING DATA
		
		// Cluster_size = 10
		// double[] clustrLat =  { 41.1574, 39.6405, 41.1756, 41.2415, 41.1613, 41.1433, 41.1788, 41.162, 41.1652, 41.1546};
		// double[] clustrLong = { -8.5731, -8.7696, -7.7895, -8.6709, -8.6284, -8.6146, -8.595, -8.6496, -8.6762, -8.6056};
		
		// Cluster_size = 11
		// double[] clustrLat =  { 41.1565, 39.6405, 41.1756, 41.1653, 41.1765, 41.144, 41.1752, 41.1579, 41.1629, 41.1519, 41.2415};
		// double[] clustrLong = { -8.5716, -8.7696, -7.7895, -8.6779, -8.6175, -8.6149, -8.592, -8.6318, -8.6527, -8.6046, -8.6716};
		
		// Cluster_size = 12
		// double[] clustrLat =  { 41.1727,   39.6248,    41.129,   41.1652,   41.1751,   41.1459,   41.3361,   41.1575,   41.1604,     41.16,   41.2414,   41.1659};
		// double[] clustrLong = { -8.5401,   -8.7766,    -7.592,   -8.6605,   -8.6051,     -8.61,    -8.315,    -8.626,   -8.6445,   -8.5836,   -8.6714,   -8.6816};
		
		// Cluster_size = 13
		// double[] clustrLat =  { 41.1602, 39.6248, 41.129, 41.1656, 41.1827, 41.1434, 41.3361, 41.1612, 41.1592, 41.1725, 41.2413, 41.1659, 41.1529};
		// double[] clustrLong = { -8.5832, -8.7766, -7.592, -8.6605, -8.6051, -8.6147, -8.315, -8.6274, -8.6447, -8.5398, -8.6717, -8.6815, -8.6053};
		
		// Cluster_size = 14
		// double[] clustrLat =  { 41.1724,    39.6248,     41.129,    41.1747,    41.1826,    41.1435,    41.3361,    41.1611,    41.1551,    41.1602,    41.2426,    41.1574,    41.1529,    41.1782};
		// double[] clustrLong = { -8.5398,    -8.7766,     -7.592,     -8.653,     -8.605,    -8.6146,     -8.315,    -8.6274,    -8.6448,    -8.5832,    -8.6708,    -8.6697,    -8.6053,    -8.6876};
		
		// Cluster_size = 15
		double[] clustrLat =  { 41.1767,     40.909,     41.129,    41.1747,    41.1826,    41.1444,     41.341,    41.1611,     41.155,    41.1601,    41.2426,    41.1576,    41.1528,    41.1782,    39.2218};
		double[] clustrLong = { -8.5408,    -8.5939,     -7.592,     -8.653,    -8.6051,    -8.6145,    -8.3149,    -8.6275,    -8.6448,    -8.5832,    -8.6709,    -8.6697,    -8.6052,    -8.6877,    -8.9714};
		
		// Cluster_size = 16
		// double[] clustrLat =  { 41.0183,    40.4565,     41.132,    41.1747,     41.181,     41.145,    41.1746,     41.161,    41.1554,    41.1528,    41.2426,    41.1576,    41.1523,    41.1782,    39.1591,     41.333};
		// double[] clustrLong = { -8.6103,     -8.525,    -7.5942,     -8.653,    -8.6062,    -8.6145,    -8.5841,    -8.6276,    -8.6448,    -8.5744,    -8.6708,    -8.6697,    -8.6042,    -8.6876,     -9.008,    -8.3373};
		
		// Cluster_size = 17
		// double[] clustrLat =  {  41.145,    39.6248,      41.17,    41.1606,    41.2406,    41.1418,    41.1572,     41.338,     41.172,    41.1655,    41.1468,    41.1659,     41.129,      41.16,    41.1681,     41.235,     41.182};
		// double[] clustrLong = { -8.5829,    -8.7766,    -8.5341,    -8.6286,    -8.6732,    -8.6185,    -8.5987,    -8.3137,    -8.5796,    -8.6608,    -8.6097,    -8.6816,     -7.592,    -8.6453,    -8.6124,    -8.6203,    -8.6008};
		
		// Cluster_size = 18
		// double[] clustrLat =  {  41.145,    39.6248,      41.17,    41.1611,    41.2419,    41.1421,    41.1572,     41.338,     41.172,    41.1574,    41.1468,    41.1781,     41.129,    41.1554,    41.1681,    41.2356,     41.182,    41.1742};
		// double[] clustrLong = { -8.5829,    -8.7766,    -8.5341,    -8.6286,    -8.6724,    -8.6183,    -8.5987,    -8.3137,    -8.5796,    -8.6698,    -8.6097,    -8.6876,     -7.592,    -8.6452,    -8.6124,    -8.6199,    -8.6008,    -8.6536};
		
		// Cluster_size = 19
		// double[] clustrLat =  { 41.1468,    39.6248,    41.1736,    41.1611,    41.2419,    41.1421,    41.1559,    41.1451,    41.1712,    41.1574,    41.1466,    41.1781,    41.3462,    41.1554,    41.1681,    41.2361,     41.182,    41.1742,     41.127};
		// double[] clustrLong = { -8.5837,    -8.7766,    -8.5624,    -8.6286,    -8.6724,    -8.6184,    -8.6003,    -8.5171,    -8.5859,    -8.6698,    -8.6098,    -8.6876,    -8.3122,    -8.6452,    -8.6125,    -8.6199,    -8.6012,    -8.6536,    -7.5907};
		
		// Cluster_size = 20
		// double[] clustrLat =  { 41.1472,    39.2218,    41.1723,    41.1612,    41.2419,    41.1437,     41.156,    41.1629,    41.1713,    41.1575,    41.1465,    41.1781,    41.3481,    41.1552,    41.1681,     41.236,     41.182,    41.1741,     41.127,    40.9037};
		// double[] clustrLong = { -8.5838,    -8.9714,    -8.5632,    -8.6286,    -8.6724,     -8.618,    -8.6003,     -8.519,    -8.5859,    -8.6698,    -8.6096,    -8.6876,    -8.3114,    -8.6452,    -8.6125,      -8.62,    -8.6012,    -8.6535,    -7.5907,    -8.5955};
		
		final int CLUSTER_SIZE = clustrLat.length;
		
		double[] sumDistToClust = new double[CLUSTER_SIZE];
		int[] countPointOfClust = new int[CLUSTER_SIZE];
		
		fOut.println("\"LATITUDE\",\"LONGITUDE\",\"COST\",\"DISTANCE\",\"CLUSTER_IDX\"");
		for (int ii = 0; ii < LIMIT; ii++) {
			String fileIn = filename + ii + format;
			System.out.println("Parsing : " + fileIn);
			Input inp = new Input(fileIn);
			Data data;
			Data dummy = new Data();
			int count = 0;
			
			ArrayList<Data> hue = new ArrayList<Data>();
			
			do {
				try {
					data = inp.nextData();
					if (data == null) break;
					hue.add(data);
				}
				catch (Exception ex) {
					data = dummy;
				}
			} while (data != null);
			
			Collections.sort(hue);
			
			for (Data dataa : hue) {
				ArrayList<Coordinate> arc = dataa.POLYLINE;
				double centroidLat = 0;
				double centroidLong = 0;
				double startLat = 0;
				double startLong = 0;
				double endLat = 0;
				double endLong = 0;
				int n = arc.size();
				
				double distance = 0;
				for (int i=0; i<n; i++) {
					Coordinate c = arc.get(i);
					
					centroidLat += c.latitude/n;
					centroidLong += c.longitude/n;
					
					if (i > 0) {
						Coordinate d = arc.get(i-1);
						distance += HaversineAlgorithm.HaversineInM(c.latitude,c.longitude,d.latitude,d.longitude);
					}
					if (i == 0) {
						startLat = c.latitude;
						startLong = c.longitude;
					}
					if (i == n-1) {
						endLat = c.latitude;
						endLong = c.longitude;
					}
				}
				
				int cluster1 = -1;
				int cluster2 = -1;
				double closeClstrDist1 = 999999;
				double closeClstrDist2 = 999999;
				Coordinate start = new Coordinate(startLat,startLong);
				Coordinate end = new Coordinate(endLat,endLong);
				
				for (int i = 0; i < CLUSTER_SIZE; i++) {
					Coordinate clstrCoord = new Coordinate(clustrLat[i],clustrLong[i]);
					
					double distToClst;
					
					distToClst = getEuclidDist(start,clstrCoord);
					if (distToClst < closeClstrDist1) {
						cluster1 = i;
						closeClstrDist1 = distToClst;
					}
					
					distToClst = getEuclidDist(end,clstrCoord);
					if (distToClst < closeClstrDist2) {
						cluster2 = i;
						closeClstrDist2 = distToClst;
					}
				}
				
				sumDistToClust[cluster1] += closeClstrDist1;
				sumDistToClust[cluster2] += closeClstrDist2;
				countPointOfClust[cluster1]++;
				countPointOfClust[cluster2]++;
				
				Coordinate centroid = new Coordinate(centroidLat,centroidLong);
				if (distance > 1E-9 && dataa.DAY_TYPE == 'A') {
					fOut.println("\"" + startLat + "\",\"" + startLong + "\",\"" + fare((n-1)/4,distance) + "\",\"" + distance + "\",\"" + cluster1 + "\"");
					fOut.println("\"" + endLat + "\",\"" + endLong + "\",\"" + fare((n-1)/4,distance) + "\",\"" + distance + "\",\"" + cluster2 + "\"");
					fOut.flush();
				}
			}
			
			// CALCULATE DBI
			
		}
		System.out.println("Calculating DBI... ");
		
		double totalDBIndex = 0.0;
		for (int i = 0; i < CLUSTER_SIZE; i++) {
			double maxDBIndex = 0.0;
			double clusterSum1 = sumDistToClust[i] / countPointOfClust[i];
			for (int j = 0; j < CLUSTER_SIZE; j++) {
				if (i != j) {
					double dbIndex = (clusterSum1 + sumDistToClust[j] / countPointOfClust[j]) / getEuclidDist(new Coordinate(clustrLat[i],clustrLong[i]),new Coordinate(clustrLat[j],clustrLong[j]));
					maxDBIndex = Math.max(maxDBIndex, dbIndex);
				}
			}
			totalDBIndex += maxDBIndex;
		}
		double daviesBouldinIndex = totalDBIndex / CLUSTER_SIZE;
		
		fOut.println(daviesBouldinIndex);
		fOut.flush();
	}
	
	public static double fare(int minute, double distance)
	{
		double baseRate = 3.10;
		double mileRate = 0.5;
		double minuteRate = 0.5;
		
		double n = baseRate;
		
		if (distance <= 1.0/5) {
			n = baseRate;
		}
		else if (distance < minute / 5.0) {
			distance -= 1.0/5;
			n += mileRate * distance;
		}
		else {
			n += minute * minuteRate;
		}
		return n;
	}
	
	public static double getEuclidDist(Coordinate a, Coordinate b) {
		double diffLat = a.latitude - b.latitude;
		double diffLong = a.longitude - b.longitude;
		return Math.sqrt((diffLat*diffLat) + (diffLong*diffLong));
	}
}

class HaversineAlgorithm {

    static final double _eQuatorialEarthRadius = 6378.1370D;
    static final double _d2r = (Math.PI / 180D);

    public static double HaversineInM(double lat1, double long1, double lat2, double long2) {
        return (HaversineInKM(lat1, long1, lat2, long2) / 1.6f);
    }

    public static double HaversineInKM(double lat1, double long1, double lat2, double long2) {
        double dlong = (long2 - long1) * _d2r;
        double dlat = (lat2 - lat1) * _d2r;
        double a = Math.pow(Math.sin(dlat / 2D), 2D) + Math.cos(lat1 * _d2r) * Math.cos(lat2 * _d2r)
                * Math.pow(Math.sin(dlong / 2D), 2D);
        double c = 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));
        double d = _eQuatorialEarthRadius * c;

        return d;
    }

}