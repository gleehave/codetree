import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] sticks;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        
        sticks = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            sticks[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n+1];
        for(int i=1; i<=n; i++){
            dp[i] = 0;
        }

        for(int i=1; i<=n; i++){ // 무게

            dp[i] = sticks[i];
            for(int j=0; j<i; j++){ // 스틱
                if (i - j < 0) continue;

                dp[i] = Math.max(dp[i], dp[i - j] + sticks[j]);
            }
        }
        System.out.println(dp[n]);

    }
}