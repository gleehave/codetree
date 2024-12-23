import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = Integer.MIN_VALUE;
        } // arr

        dp[0] = 0;
        for(int i=1; i<n; i++){
            for(int j=0; j<i; j++){
                if (dp[j] == Integer.MIN_VALUE) continue;

                if (j + arr[j] >= i){
                    dp[i] = Math.max(dp[j]+1, dp[i]);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for(int n: dp) max = Math.max(max, n);
        System.out.println(max);
    }
}