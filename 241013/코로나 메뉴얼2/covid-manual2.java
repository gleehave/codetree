import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] count = new int[4];

        for(int i=0; i<3; i++){
            String[] data = br.readLine().split(" ");
            switch(data[0]){
                case "Y":
                    if (Integer.parseInt(data[1]) >= 37) count[0]++;
                    else count[2]++;
                    break;
                case "N":
                    if (Integer.parseInt(data[1]) >= 37) count[1]++;
                    else count[3]++;
                    break;
            }
        }
        if (count[0] < 2){
            System.out.println(count[0]+ " "+count[1] +" "+count[2]+" "+count[3]);
        } else {
            System.out.println(count[0]+ " "+count[1] +" "+count[2]+" "+count[3]+" E");            
        }
    }
}