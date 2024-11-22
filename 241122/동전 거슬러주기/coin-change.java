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
            dp[i] = Integer.MAX_VALUE;
        }

        for(int i=1; i<=m; i++){ // m금액까지 
            for(int j=1; j<=n; j++){
                if (i >= coin[j]){
                    if (dp[i - coin[j]] == Integer.MAX_VALUE) continue;

                    if (coin[j] == i){
                        dp[i] = 1;
                    } else {
                        dp[i] = Math.min(dp[i], dp[i - coin[j]] + 1);
                    }   
                }
            }
        }
        if (dp[m] == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(dp[m]);
    }
}