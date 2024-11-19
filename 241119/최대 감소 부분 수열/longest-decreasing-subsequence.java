
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

        dp = new int[n];
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = Integer.MIN_VALUE;
        }

        dp[0] = 1;
        for(int i=1; i<n; i++){
            for(int j=0; j<i; j++){
                if (dp[j] == Integer.MIN_VALUE) continue;

                if (arr[j] > arr[i]){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }

        int result = Integer.MIN_VALUE;
        for(int len: dp) result = Math.max(result, len);
        
        System.out.println(result);
    }
}