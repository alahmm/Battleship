package inputstream;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

class Main {
/*    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line = br.readLine();
        char[] array = line.toCharArray();
        for (char var : array
             ) {
            System.out.print(Integer.valueOf(var));
        }

    */
    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line = br.readLine();
        char[] array = line.toCharArray();
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[array.length - 1 - i]);
        }
        br.close();

    }
}