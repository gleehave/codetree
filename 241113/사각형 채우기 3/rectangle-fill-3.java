/*
memo[1] = 2
memo[2] = 7
memo[3] = memo[2] * 2 = memo[n-1] * 2
memo[4] = memo[n-2] * 2 + memo[n-2] 

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

        if (n == 1){
            System.out.println(2);
        } else if (n==2){
            System.out.println(7);
        } else {
            memo[1] = 2;
            memo[2] = 7;

            for(int i=3; i<=n; i++){
                if (i % 2 != 0) memo[i] = (memo[i-1] % 1000000007)* 2;
                memo[i] = (memo[i-2] * 2 + memo[i-2]) % 1000000007;
            }
            System.out.println(memo[n]);
        }

    }
}