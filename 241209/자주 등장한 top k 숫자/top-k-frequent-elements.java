import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static Map<Integer, Integer> map;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        map = new HashMap<>();
        for(int i=0; i<n; i++){
            int key = Integer.parseInt(st.nextToken());
            if (map.containsKey(key)){
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }

        Queue<int[]> queue = new LinkedList<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                if (a[1] == b[1]) return b[0] - a[0];
                return a[1] - b[1];
            }
        });

        for(int key : map.keySet()){
            queue.offer(new int[]{key, map.get(key)});
        }

        // Collections.sort(queue);
        for(int[] d: queue){
            System.out.println(d[0] +" " + d[1]);
        }
    }
}