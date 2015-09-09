import java.io.*;
import java.util.*;

public class DayTester
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader fIn = new BufferedReader(new FileReader("train_day_-1.csv"));
		StringTokenizer st;
		String inp;
		Object EOF = null;
		
		//TESTER
		fIn.readLine();
		final int LIMIT = 25;
		int timestampPos = 5; 
		while ((inp = fIn.readLine()) != EOF) {
		// for (int i = 0; i < LIMIT; i++) {
			// inp = fIn.readLine();
			st = new StringTokenizer(inp,",");
			for (int j = 0; j < timestampPos; j++) {
				st.nextToken();
			}
			String timestamp = st.nextToken();
			Calendar cal = Calendar.getInstance();
			Date date = new Date(Long.parseLong(timestamp.substring(1,timestamp.length()-1))*1000);
			cal.setTime(date);
			System.out.println(date);
		}
		
		// String filename = "train_day_";
		// int part = -1;
		// String format = ".csv";
		// PrintWriter fOut = new PrintWriter(new File(filename + part + format));

		// int timestampPos = 5;    // Position of timestamp
		// int prevDay = 0;
		
		// fIn.readLine(); // Remove Column Name
		
		// while ((inp = fIn.readLine()) != EOF) {
			// st = new StringTokenizer(inp,",");
			// for (int i = 0; i < timestampPos; i++) {
				// st.nextToken();
			// }
			// String timestamp = st.nextToken();
			// long time = Long.parseLong(timestamp.substring(1,timestamp.length()-1))*1000;
			// Calendar cal = Calendar.getInstance();
			// Date date = new Date(time);
			// cal.setTime(date);
			
			// if (cal.get(Calendar.DAY_OF_MONTH) != prevDay) {
				// part++;
				// prevDay = cal.get(Calendar.DAY_OF_MONTH);
				// fOut = new PrintWriter(filename + part + format);
			// }
			// fOut.println(inp);
		// }
	}
}
