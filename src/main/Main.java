package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception {

        ArrayList<String> mem = new ArrayList<>();

        BufferedReader in  = new BufferedReader(new FileReader(Input.INPUT_DIRECTORY+"train_month_1_filtered.csv"));
        BufferedWriter out = new BufferedWriter(new FileWriter(Input.INPUT_DIRECTORY+"sampling.csv"));

        String inp = "";

        Random rand = new Random();

        do {
            try {
                inp = in.readLine();
                mem.add(inp);
            } catch (Exception ex) {
            }
        } while (inp != null);
        
        ArrayList<String> hue = new ArrayList<>();
        for (String s : mem) {
            hue.add(s);
            if (hue.size() == 400) {
                int huehue = rand.nextInt(400);
                out.write(hue.get(huehue) + "\n");
                hue.clear();
            }
        }

        out.close();
    }
}
