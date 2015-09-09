import java.io.*;
import java.util.*;

public class CentroidCostAdder
{
	public static void main(String[] args) throws Exception
	{
		String filename = "train_day_";
		String format = ".csv";
		final int LIMIT = 20;
		final Object EOF = null;
		String fileOut = "train_start_end_20.csv";
		PrintWriter fOut = new PrintWriter(new BufferedWriter(new FileWriter(fileOut,false)));
		fOut.println("\"LATITUDE\",\"LONGITUDE\"");
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
				
				Coordinate centroid = new Coordinate(centroidLat,centroidLong);
				if (distance > 1E-9 && dataa.DAY_TYPE == 'A') {
					// fOut.println(dataa + ",\"" + startLat + "\",\"" + startLong + "\",\"" + fare((n-1)/4,distance) + "\",\"" + distance + "\"");
					fOut.println("\"" + startLat + "\",\"" + startLong + "\"");
					// fOut.flush();
					// fOut.println(dataa + ",\"" + endLat + "\",\"" + endLong + "\",\"" + fare((n-1)/4,distance) + "\",\"" + distance + "\"");
					fOut.println("\"" + endLat + "\",\"" + endLong + "\"");
					fOut.flush();
				}
			}
			// while ((fIn = fIn.readLine()) != EOF) {
				// st = new StringTokenizer(inp,"\"");
				// String polyline = null;
				// while (st.hasMoreTokens()) {
					// polyline = st.nextToken();
				// }
				// st = new StringTokenizer(polyline,"[,]");
				// double centroidX = 0;
				// double centroidY = 0;
				// int count = 0;
				// while (st.hasMoreTokens()) {
					// double x = Double.parseDouble(st.nextToken());
					// double y = Double.parseDouble(st.nextToken());
					// centroidX += x;
					// centroidY += y;
					// count++;
				// }
				// if (count > 0) {
					// centroidX /= count;
					// centroidT /= count;
					// fOut.println(inp + ",\"[" + centroidX + "," + centroidY + "]\"");
				// }
				// else {
					// fOut.println(inp + ",\"[]\"");					
				// }
		}
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