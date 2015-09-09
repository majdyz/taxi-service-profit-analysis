import java.io.*;
import java.util.*;

public class MonthClusterer
{
	public static void main(String[] args) throws Exception
	{
		String filename = "train_month_";
		String formatIn = ".csv";
		String formatOut = "_filtered.csv";
		final int LIMIT =  12;
		final Object EOF = null;
		
		double[] clustrLat =  { 41.1767,     40.909,     41.129,    41.1747,    41.1826,    41.1444,     41.341,    41.1611,     41.155,    41.1601,    41.2426,    41.1576,    41.1528,    41.1782,    39.2218};
		double[] clustrLong = { -8.5408,    -8.5939,     -7.592,     -8.653,    -8.6051,    -8.6145,    -8.3149,    -8.6275,    -8.6448,    -8.5832,    -8.6709,    -8.6697,    -8.6052,    -8.6877,    -8.9714};
		final int CLUSTER_SIZE = clustrLat.length;
		
		double[] sumDistToClust = new double[CLUSTER_SIZE];
		int[] countPointOfClust = new int[CLUSTER_SIZE];
		
		for (int ii = 0; ii < LIMIT; ii++) {
			String fileIn = filename + (ii+1) + formatIn;
			System.out.println("Parsing : " + fileIn);
			Input inp = new Input(fileIn);
			PrintWriter fOut = new PrintWriter(new BufferedWriter(new FileWriter(filename + (ii+1) + formatOut)));
			fOut.println("\"TIMESTAMP\",\"LATITUDE\",\"LONGITUDE\",\"COST\",\"DISTANCE\",\"CLUSTER_IDX\"");
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
				
				if (cluster1 > -1 && cluster2 > -1) {
					sumDistToClust[cluster1] += closeClstrDist1;
					sumDistToClust[cluster2] += closeClstrDist2;
					countPointOfClust[cluster1]++;
					countPointOfClust[cluster2]++;
				}
				
				Coordinate centroid = new Coordinate(centroidLat,centroidLong);
				if (distance > 1E-9 && dataa.DAY_TYPE == 'A') {
					fOut.println("\"" + dataa.TIMESTAMP + "\",\"" + startLat + "\",\"" + startLong + "\",\"" + fare((n-1)/4,distance) + "\",\"" + distance + "\",\"" + cluster1 + "\"");
					fOut.println("\"" + dataa.TIMESTAMP + "\",\"" + endLat + "\",\"" + endLong + "\",\"" + fare((n-1)/4,distance) + "\",\"" + distance + "\",\"" + cluster2 + "\"");
					fOut.flush();
				}
			}
			
		}
			
		// CALCULATE DBI
		// System.out.println("Calculating DBI... ");
		
		// double totalDBIndex = 0.0;
		// for (int i = 0; i < CLUSTER_SIZE; i++) {
			// double maxDBIndex = 0.0;
			// double clusterSum1 = sumDistToClust[i] / countPointOfClust[i];
			// for (int j = 0; j < CLUSTER_SIZE; j++) {
				// if (i != j) {
					// double dbIndex = (clusterSum1 + sumDistToClust[j] / countPointOfClust[j]) / getEuclidDist(new Coordinate(clustrLat[i],clustrLong[i]),new Coordinate(clustrLat[j],clustrLong[j]));
					// maxDBIndex = Math.max(maxDBIndex, dbIndex);
				// }
			// }
			// totalDBIndex += maxDBIndex;
		// }
		// double daviesBouldinIndex = totalDBIndex / CLUSTER_SIZE;
		
		// fOut.println(daviesBouldinIndex);
		// fOut.flush();
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