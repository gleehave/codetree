import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] coin;
    static int[][] dp; // n층 / coin 획득 값

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        
        int maxValue = 0;
        coin = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            coin[i] = Integer.parseInt(st.nextToken());
            maxValue += coin[i];
        }

        dp = new int[n + 1][maxValue + 1];
        dp[1][1] = coin[1];

        for(int i=1; i<=n; i++){

            for(int j=1; j<=i; j++){
                if (j - 2 < 0) continue;
                dp[i][j] = Math.max(dp[i-1][j-1] + coin[i], dp[i-2][j-1] + coin[i]);                
            }
        }

        for(int i=0; i<=n; i++){
            for(int j=0; j<=maxValue; j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
    }
}