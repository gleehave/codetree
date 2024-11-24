import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[] time;
    static int[] exp;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        time = new int[n+1];
        exp = new int[n+1];

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            int e = Integer.parseInt(st.nextToken());
            int t= Integer.parseInt(st.nextToken());
            exp[i] = e;
            time[i] = t;
        }

        dp = new int[m+1];
        for(int i=1; i<=m; i++){
            dp[i] = 0;
        }

        for(int i=1; i<=n; i++){
            dp[exp[i]] = time[i];
            for(int j=m; j>=0; j--){
                if (j - exp[i] < 0) continue;

                dp[j] = Math.max(dp[j], dp[j - exp[i]] + time[i]);
            }
        }

        System.out.println(Arrays.toString(dp));
        
        int ans = Integer.MIN_VALUE;
        for(int t: dp) ans = Math.max(ans, t);
        System.out.println(ans);
    }
}