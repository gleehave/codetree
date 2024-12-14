import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static Map<Long, List<Long>> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            
            if (map.containsKey(x)){
                map.get(x).add(y);
            } else {
                map.put(x, new ArrayList<>());
                map.get(x).add(y);
            }
        }

        long sum = 0;
        for(long key : map.keySet()){
            Collections.sort(map.get(key));
            sum += map.get(key).get(0);
        }

        System.out.println(sum);
    }
}