import java.util.*;
import java.io.*;

class Pair implements Comparable<Pair>{
    int x1, x2;

    public Pair(int x1, int x2){
        this.x1 = x1;
        this.x2 = x2;
    }

    @Override
    public int compareTo(Pair p){
        if (x1 == p.x1) return this.x2 - p.x2;
        return this.x1 - p.x1; 
    }
}


public class Main {
    static int n;
    static Pair[] arr;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        
        arr = new Pair[n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = new Pair(
                Integer.parseInt(st.nextToken()), 
                Integer.parseInt(st.nextToken())        
            );
        }

        Arrays.sort(arr);
        
        dp = new int[1000];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 1;
        
        for(int i=1; i<n; i++){
            for(int j=0; j<i; j++){
                if (dp[j] == Integer.MIN_VALUE) continue;

                if (inRange(arr[i].x1, arr[i].x2, arr[j].x1, arr[j].x2)){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        int max = Integer.MIN_VALUE;
        for(int i=0; i<dp.length; i++){
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }

    static boolean inRange(int x1, int x2, int nx1, int nx2) {
        return x2 < nx1 || nx2 < x1;
    }    
}