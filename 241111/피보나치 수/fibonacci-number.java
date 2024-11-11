/*
N=5
1 1 2 3 5


memo[i] = memo[i-2] + memo[i-1]

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
        if (n==1){
            System.out.println(1);
        } else {
            memo[1] = 1;
            memo[2] = 1;

            for(int i=3; i<=n; i++){
                memo[i] = memo[i-1] + memo[i-2];
            }

            System.out.println(memo[n]);
        }
    }
}