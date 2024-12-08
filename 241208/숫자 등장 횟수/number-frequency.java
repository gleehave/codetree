import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception{
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            int key = Integer.parseInt(st.nextToken());
            map.put(key, map.getOrDefault(key, 0)+1);
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++){
            int key = Integer.parseInt(st.nextToken());
            System.out.print(map.getOrDefault(key, 0)+" ");
        }

    }
}