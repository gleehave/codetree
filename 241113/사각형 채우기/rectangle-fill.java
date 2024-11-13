/*

memo[1] = 1
memo[2] = 2
memo[3] = memo[2] + memo[1]
memo[4] = memo[3] + memo[1]
*/

import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] memo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        memo = new int[n+1];

        memo[1] = 1;
        memo[2] = 2;

        for(int i=3; i<=n; i++){
            memo[i] = (memo[i-1] + memo[i-2]) % 10007;
        }

        System.out.println(memo[n]);

    }
}