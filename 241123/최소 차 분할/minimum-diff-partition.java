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
        int totalSum = 0;
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            totalSum += arr[i];
        }

        dp = new int[totalSum+1];

        for(int i=0; i<n; i++){
            for(int j=totalSum; j>=0; j--){
                if (j - arr[i] < 0) continue;
                dp[j] = Math.max(dp[j], dp[j - arr[i]] + 1);
            }
        }
        
        int total = Integer.MAX_VALUE;
        for(int i=1; i<=totalSum; i++){
            total = Math.min(total, Math.abs(Math.abs(totalSum - i) - i));
        }
        System.out.println(total);   
    }
}