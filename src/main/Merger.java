import java.io.*;
import java.util.*;

public class Merger
{
	public static void main(String[] args) throws IOException
	{
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("train_centroid_cost.csv",false)));
		out.println("\"TRIP_ID\",\"CALL_TYPE\",\"ORIGIN_CALL\",\"ORIGIN_STAND\",\"TAXI_ID\",\"TIMESTAMP\",\"DAY_TYPE\",\"CENTROID_LATITUDE\",\"CENTROID_LONGITUDE\",\"COST\"");
		final int LIMIT = 365;
		final Object EOF = null;
		String filename1 = "train_day_";
		String filename2 = "_centroidCost.csv";
		for (int i = 0; i < LIMIT; i++) {
			String file = filename1 + i + filename2;
			System.out.print("Joining... " + file);
			BufferedReader fIn = new BufferedReader(new FileReader(file));
			String inp;
			while ((inp = fIn.readLine()) != EOF) {
				out.println(inp);
				out.flush();
			}
			System.out.println("  COMPLETE");
		}
		out.close();
	}
}