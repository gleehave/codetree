/*
memo[1] = 0
memo[2] = 1
memo[3] = 1
memo[4] = memo[2] = memo[2] + memo[1]
memo[5] = memo[2] + memo[3] = memo[n-2] + memo[n-3]

*/

import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] memo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        if (n<=3){
            System.out.println(1);
            return;
        }
        
        memo = new int[n+1]; 
        memo[1] = 0;
        memo[2] = 1;
        memo[3] = 1;

        for(int i=4; i<=n ;i++){
            memo[i] = (memo[i-2] + memo[i-3]) % 10007;
        }
        System.out.println(memo[n]);
    }
}