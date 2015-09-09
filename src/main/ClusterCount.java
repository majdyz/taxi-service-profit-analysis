import java.util.*;
import java.io.*;

public class ClusterCount
{
	public static void main(String[] args) throws IOException
	{
		String filename = "train_month_";
		String formatIn = "_filtered.csv";
		String formatOut = "_clusterInfo.csv";
		
		double[] clustrLat =  { 41.1767,     40.909,     41.129,    41.1747,    41.1826,    41.1444,     41.341,    41.1611,     41.155,    41.1601,    41.2426,    41.1576,    41.1528,    41.1782,    39.2218};
		double[] clustrLong = { -8.5408,    -8.5939,     -7.592,     -8.653,    -8.6051,    -8.6145,    -8.3149,    -8.6275,    -8.6448,    -8.5832,    -8.6709,    -8.6697,    -8.6052,    -8.6877,    -8.9714};
		final int CLUSTER_SIZE = clustrLat.length;
		final int LIMIT = 12;
		
		for (int ii = 0; ii < LIMIT; ii++) {
			int num = ii+1;
			BufferedReader in = new BufferedReader(new FileReader(filename + num + formatIn));
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + num + formatOut)));
			System.out.print("Processing: " + filename + num + formatIn + ".");
			String inp;
			StringTokenizer st;
			final Object EOF = null;
			int[] clustrCount = new int[CLUSTER_SIZE];
			inp = in.readLine(); // ELIMINATE COLUMN NAME
			while ((inp = in.readLine()) != EOF) {
				st = new StringTokenizer(inp,"\",");
				long timestamp = Long.parseLong(st.nextToken());
				double latitude = Double.parseDouble(st.nextToken());
				double longitude = Double.parseDouble(st.nextToken());
				double cost = Double.parseDouble(st.nextToken());
				double dist = Double.parseDouble(st.nextToken());
				int clusterIdx = Integer.parseInt(st.nextToken());
				clustrCount[clusterIdx]++;
			}
			out.println("\"LATITUDE\",\"LONGITUDE\",\"COUNT\"");
			for (int i = 0; i < CLUSTER_SIZE; i++) {
				out.println("\"" + clustrLat[i] + "\",\"" + clustrLong[i] + "\",\"" + clustrCount[i] + "\"");
			}
			out.flush();
			System.out.println(" Outputted to " + filename + num + formatOut + ".");
		}
	}
}