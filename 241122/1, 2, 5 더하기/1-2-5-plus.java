import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] num = new int[]{0, 1, 2, 5};
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        n = Integer.parseInt(br.readLine());
        dp = new int[n+1];

        for(int i=1; i<=n; i++){
            dp[i] = Integer.MIN_VALUE;
        }
        System.out.println(Arrays.toString(dp));

        for(int i=1; i<=n; i++){

            for(int j=1; j<=3; j++){
                if ()
            }
        }
        System.out.println(Arrays.toString(dp));
    }
}