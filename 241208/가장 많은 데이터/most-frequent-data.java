import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static Map<String, Integer> map = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++){
            String key = br.readLine();
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        int max = Integer.MIN_VALUE;
        for(String key:map.keySet()){
            max = Math.max(max, map.get(key));
        }
        System.out.println(max);
    }
}