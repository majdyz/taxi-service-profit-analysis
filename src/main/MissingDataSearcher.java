import java.io.*;
import java.util.*;

public class MissingDataSearcher
{
	public static void main(String[] args) throws IOException
	{
		final int LIMIT = 365;
		final Object EOF = null;
		String filename = "train_day_";
		String format = ".csv";
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("MISSING.txt")));
		for (int i = 0; i < LIMIT; i++) {
			String file = filename + i + format;
			System.out.println("Searching : " + file);
			BufferedReader fIn = new BufferedReader(new FileReader(file));
			int linecount = 0;
			String inp;
			StringTokenizer st;
			while ((inp = fIn.readLine()) != EOF) {
				linecount++;
				st = new StringTokenizer(inp,"\"");
				String token = null;
				while (st.hasMoreTokens()) {
					token = st.nextToken();
					if (token.equalsIgnoreCase("True") || token.equalsIgnoreCase("False")) {
						break;
					}
				}
				if (token != null && token.equalsIgnoreCase("True")) {
					out.println("FILE = " + file + "; LINE = " + linecount);
				}
			}
		}
		out.close();
	}
}