/*
for(1-N){
    now = arr[i];
    count = new HashMap<>();

    for(j=i+1; j<N; j++){
        Long diff = now + arr[j];

        if (count.containsKey(diff)) ans += count.get(diff);
        
        if (!count.containsKey(arr[i])) count.put(arr[i], 1);
        else count.put(arr[i], count.get(arr[i]) + 1);
    }
}
*/

import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static long[] arr;
    static Map<Long, Integer> count;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new long[n];
        for(int i=0; i<n; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        int ans = 0;
        count = new HashMap<>();
        for(int i=0; i<n; i++){
            long now = arr[i];
            for(int j=i+1; j<n; j++){
                Long diff = now + arr[j];

                 if (count.containsKey(diff)) ans += count.get(diff);
        
                 if (!count.containsKey(arr[i])) count.put(arr[i], 0);
                  else count.put(arr[i], count.get(arr[i]) + 1);
            }
        }
        System.out.println(ans);
    }
}