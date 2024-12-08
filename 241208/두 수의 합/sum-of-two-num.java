import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static List<Integer> removeKey;
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            int key = Integer.parseInt(st.nextToken());
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        int count = 0;
        while(n-- > 0){
            removeKey = new ArrayList<>();

            for(int key : map.keySet()){
                if (map.get(key) <= 0) continue;

                if (map.containsKey(k - key)){
                    count++;
                    int num = map.get(key);
                    map.put(key, --num);

                    num = map.get(k - key);
                    map.put(k-key, --num);
                }
            }
        }

        System.out.println(count);
    }
}