import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n+1];
        dp = new int[m+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<=m; i++){
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        for(int i=m; m>=0; i--){

            for(int j=1; j<=n; j++){
                if (i >= arr[j]){
                    if (dp[i - arr[j]] == Integer.MAX_VALUE) continue;

                    dp[j] = Math.min(dp[j], dp[i - arr[j]] + 1);
                }
            }
        }

    }
}