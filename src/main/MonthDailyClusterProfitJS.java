import java.util.*;
import java.io.*;

public class MonthDailyClusterProfitJS
{
	public static void main(String[] args) throws IOException
	{
		double[] clustrLat =  { 41.1767,     40.909,     41.129,    41.1747,    41.1826,    41.1444,     41.341,    41.1611,     41.155,    41.1601,    41.2426,    41.1576,    41.1528,    41.1782,    39.2218};
		double[] clustrLong = { -8.5408,    -8.5939,     -7.592,     -8.653,    -8.6051,    -8.6145,    -8.3149,    -8.6275,    -8.6448,    -8.5832,    -8.6709,    -8.6697,    -8.6052,    -8.6877,    -8.9714};
		final int CLUSTER_SIZE = clustrLat.length;
		final int[] DAY_IN_MONTH = { 31, 31, 30, 31, 30, 31, 31, 28, 31, 30, 31, 30 };
		
		String filename = "train_month_";
		String formatIn = "_filtered.csv";
		String formatOut = "_dcpZamil.csv";
		final int LIMIT = 12;
		
		final Object EOF = null;
		
		for (int ii = 0; ii < LIMIT; ii++) {
			double[][] clusterProfit = new double[DAY_IN_MONTH[ii]][CLUSTER_SIZE];
			String fileIn = filename + (ii+1) + formatIn;
			String fileOut = filename + (ii+1) + formatOut;
			BufferedReader in = new BufferedReader(new FileReader(fileIn));
			System.out.print("Processing : " + fileIn + " ");
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileOut)));
			String inp;
			StringTokenizer st;
			Map<String,Integer> dateMap = new HashMap<>();
			int part = 0;
			in.readLine();
			while ((inp = in.readLine()) != EOF) {
				st = new StringTokenizer(inp,",\"");
				long timestamp = (Long.parseLong(st.nextToken()) - 28800) * 1000;
				double latitude = Double.parseDouble(st.nextToken());
				double longitude = Double.parseDouble(st.nextToken());
				double cost = Double.parseDouble(st.nextToken());
				double distance = Double.parseDouble(st.nextToken());
				int clustIdx = Integer.parseInt(st.nextToken());
				
				Date date = new Date(timestamp);
				String dateKey = date.toString().substring(0,10);
				if (!dateMap.containsKey(dateKey)) {
					dateMap.put(dateKey,part);
					part++;
				}
				
				int dayIdx = dateMap.get(dateKey);
				
				// System.out.println(dayIdx + " " + clustIdx + " " + dateKey);
				clusterProfit[dayIdx][clustIdx] += cost;
			}
			
			// out.print("\"DAY_IDX\"");
			// for (int i = 0; i < CLUSTER_SIZE; i++) {
				// out.print(",\"CLUSTER_" + (i+1) + "\"");
			// }
			// out.println();

			for (int i = 0; i < DAY_IN_MONTH[ii]; i++) {
				out.print("\"" + (i+1) + "\",");
				String temp = "";
				for (int j = 0; j < CLUSTER_SIZE; j++) {
					if (j == 0) {
						temp += "\"" + clusterProfit[i][j] + "\"";
					}
					else {
						temp += ",\"" + clusterProfit[i][j] + "\"";
					}
				}
				out.println(" { label: \"Cluster" + (i+1) +"\", fillColor: Color" + (i+1) +".rgba, strokeColor: Color" + (i+1) +".rgb, pointColor: Colors.gray.rgb, pointStrokeColor: \"#fff\", pointHighlightFill: \"#fff\", pointHighlightStroke: Colors.gray.rgba, data: [" + temp + " ] }");
			}
			out.flush();
			System.out.println("Done");
		}
	}
}