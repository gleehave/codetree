import java.io.*;
import java.util.*;

public class Main {

    static int n, k;
    static int[] number;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[n+1];    
        number = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            number[i] = Integer.parseInt(st.nextToken());
            dp[i] = Integer.MIN_VALUE;
        }

        int count = 0;
        for(int i=1; i<=n; i++){
            if (number[i] < 0) count++;

            if (count <= k){
                dp[i] = Math.max(dp[i], dp[i-1] + number[i]);
            } else {
                dp[i] = number[i];
                count = 0;                
            }
        }

        // System.out.println(Arrays.toString(dp));

        int max = Integer.MIN_VALUE;
        for(int num : dp){
            max = Math.max(max, num);
        }
        System.out.println(max);
    }
}