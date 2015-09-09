import java.io.*;
import java.util.*;

public class DayParser
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader fIn = new BufferedReader(new FileReader("train.csv"));
		StringTokenizer st;
		String inp;
		Object EOF = null;
		
		// TESTER
		// fIn.readLine();
		// final int LIMIT = 25;
		// for (int i = 0; i < LIMIT; i++) {
			// inp = fIn.readLine();
			// st = new StringTokenizer(inp,",");
			// for (int j = 0; j < timestampPos; j++) {
				// st.nextToken();
			// }
			// String timestamp = st.nextToken();
			// Calendar cal = Calendar.getInstance();
			// cal.setTime(new Date(Long.parseLong(timestamp.substring(1,timestamp.length()-1))*1000));
			// System.out.println(cal.get(Calendar.DAY_OF_MONTH));
		// }
		
		String filename = "train_day_";
		int part = 0;
		String format = ".csv";
		PrintWriter fOut = new PrintWriter(new BufferedWriter(new FileWriter(filename + part + format,true)));

		int timestampPos = 5;    // Position of timestamp
		int prevDay = 0;
		
		fIn.readLine(); // Remove Column Name
		
		Map<String,Integer> fileMap = new HashMap<>();
		
		// for (int ii = 0; ii < 3300; ii++) {
		while ((inp = fIn.readLine()) != EOF) {
			// inp = fIn.readLine();
			st = new StringTokenizer(inp,",");
			for (int i = 0; i < timestampPos; i++) {
				st.nextToken();
			}
			String timestamp = st.nextToken();
			long time = (Long.parseLong(timestamp.substring(1,timestamp.length()-1)) - 28800)*1000;		// Pull 8 hrs from GMT+8
			Date date = new Date(time);
			
			String dateKey = date.toString().substring(0,10);
			if (!fileMap.containsKey(dateKey)) {
				fileMap.put(dateKey,part);
				part++;
			}
			
			fOut = new PrintWriter(new BufferedWriter(new FileWriter(filename + fileMap.get(dateKey) + format,true)));
			// PrintWriter fOut2 = new PrintWriter(new BufferedWriter(new FileWriter(fileMap.get(dateKey) + format,true)));
			
			fOut.println(inp);
			// fOut2.println(dateKey);
			fOut.flush();
			// fOut2.flush();
			
			// if (cal.get(Calendar.DAY_OF_MONTH) != prevDay) {
				// part++;
				// prevDay = cal.get(Calendar.DAY_OF_MONTH);
				// fOut = new PrintWriter(filename + part + format);
			// }
		}
	}
}
