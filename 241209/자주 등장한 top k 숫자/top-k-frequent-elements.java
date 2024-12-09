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

        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                if (a[1] == b[1]) return b[0] - a[0];
                return b[1] - a[1];
            }
        });

        for(int key : map.keySet()){
            queue.offer(new int[]{key, map.get(key)});
        }

        for(int i=0; i<k; i++){
            int[] res = queue.poll();
            System.out.print(res[0]+" ");
        }
    }
}