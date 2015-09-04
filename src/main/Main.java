package main;

public class Main {

    public static void main(String[] args) throws Exception{
        Input input = new Input();

        Data data;
        Data dummy = new Data();

        int count = 0;
        do {
            try {
                data = input.nextData();
                count++;
                if (count % 100000 == 0) System.out.println(count);
            }
            catch (Exception ex) {
                data = dummy;
            }
        } while (data != null);

    }
}
