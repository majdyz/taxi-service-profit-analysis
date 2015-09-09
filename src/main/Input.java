package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by kadal_ijo on 9/4/2015.
 */
public class Input {
    public static  final String INPUT_DIRECTORY = "E:\\data\\gemastik\\Penyisihan\\Taxi Service\\clean\\";
    private static BufferedReader in;
    public static String header;

    public Input () throws Exception {
        in = new BufferedReader(new FileReader(INPUT_DIRECTORY));
        header = in.readLine();
    }

    private ArrayList<Coordinate> parseGPS(String inp) {
        ArrayList<Coordinate> result = new ArrayList<>();
        StringTokenizer token = new StringTokenizer(inp,"[],");

        while (token.hasMoreTokens()) {
            double longitude = Double.parseDouble(token.nextToken());
            double latitude  = Double.parseDouble(token.nextToken());
            result.add(new Coordinate(latitude,longitude));
        }

        return result;
    }

    public Data parseData (String input) {
        StringTokenizer token = new StringTokenizer(input,"[],\"");
        String temp;

        Data data = new Data();

        temp       = token.nextToken();
        data.TRIP_ID  = temp;

        temp       = token.nextToken();
        data.CALL_TYPE    = temp.length() > 0 ? temp.charAt(0) : '-';


        if (data.CALL_TYPE == 'A') {
            temp = token.nextToken();
            data.ORIGIN_CALL = temp.length() > 0 ? Integer.parseInt(temp) : -1;
        }

        if (data.CALL_TYPE == 'B') {
            temp = token.nextToken();
            data.ORIGIN_STAND = temp.length() > 0 ? Integer.parseInt(temp) : -1;
        }

        temp       = token.nextToken();
        data.TAXI_ID  = temp.length() > 0 ? Integer.parseInt(temp) : -1;

        temp       = token.nextToken();
        data.TIMESTAMP  = temp.length() > 0 ? Integer.parseInt(temp) : -1;

        temp       = token.nextToken();
        data.DAY_TYPE    = temp.length() > 0 ? temp.charAt(0) : '-';

        temp       = token.nextToken();
        data.CENTROID_LATITUDE = temp.length() > 0 ? Double.parseDouble(temp) : 0;

        temp       = token.nextToken();
        data.CENTROID_LONGITUDE = temp.length() > 0 ? Double.parseDouble(temp) : 0;

        temp       = token.nextToken();
        data.COST = temp.length() > 0 ? Double.parseDouble(temp) : 0;

        return data;
    }

    public Data nextData() throws Exception {
        String input = in.readLine();
        if (input == null) return null;
        else return parseData(input);
    }
}
