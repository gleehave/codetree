import java.io.*;
import java.util.*;

public class Main {
    static int n, min, max;
    static int[][] grid;
    static int[][] memo;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        memo = new int[n][n];
        grid = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        } // grid 입력

        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        memo[0][0] = grid[0][0];

        for(int c=1; c<n; c++){
            memo[0][c] = Math.min(memo[0][c-1], grid[0][c]);
        }

        for(int r=1; r<n; r++){
            memo[r][0] = Math.min(memo[r-1][0], grid[r][0]);
        }
    }

    
}