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
            dp[i] = -1;
        }

        for(int i=1; i<=n; i++){

            for(int j=m; j>=0; j--){
                if (j < arr[i]) continue;
                if (dp[j-arr[i]] == -1) continue;

                dp[j] = Math.max(dp[j - arr[i]] + 1, dp[i]);
            }
        }
        
        if (dp[m] == -1) System.out.println("No");
        else System.out.println("Yes");
    }
}