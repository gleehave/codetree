import java.io.*;
import java.util.*;

public class Main {

    static int n;
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
        } // grid

        if (n==1){
            System.out.println(grid[0][0]);
        } else {
            memo[0][0] = grid[0][0];
            for(int c=1; c<n; c++){
                memo[0][c] = Math.min(grid[0][c-1], grid[0][c]);
            }

            for(int r=1; r<n; r++){
                memo[r][0] = Math.min(grid[r-1][0], grid[r][0]);
            }

            for(int r=1; r<n; r++){
                for(int c=1; c<n; c++){
                    memo[r][c] = Math.min(memo[r-1][c], memo[r][c-1]);
                }
            }



            System.out.println(Math.min(grid[n-1][n-1], Math.max(memo[n-2][n-1], memo[n-1][n-2])));
        }
    }
}