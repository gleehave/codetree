import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] coin;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        coin = new int[n+1];
        dp = new int[m+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            coin[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<=m; i++){
            dp[i] = Integer.MIN_VALUE;
        }

        for(int i=1; i<=m; i++){

            for(int j=1; j<=n; j++){
                if (i >= coin[j]){

                    if (dp[i - coin[j]] == Integer.MIN_VALUE) continue;

                    dp[i] = Math.max(dp[i], dp[i - coin[j]] + 1);
                }
            }
        }
        if (dp[m] == Integer.MIN_VALUE) System.out.println(-1);
        else System.out.println(dp[m]); 
    }
}