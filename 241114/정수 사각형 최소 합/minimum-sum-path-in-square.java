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
        }

        memo[0][n-1] = grid[0][n-1];
        for(int c=n-2; c>=0 ;c--){
            memo[0][c] = memo[0][c+1] + grid[0][c];
        }

        for(int r=1; r<n; r++){
            memo[r][n-1] = memo[r-1][n-1] + grid[r][n-1];
        }

        for(int r=1; r<n; r++){
            for(int c=n-2; c>=0; c--){
                memo[r][c] = Math.min(grid[r][c] + memo[r-1][c], grid[r][c] + memo[r][c+1]);
            }
        }
        System.out.println(memo[n-1][0]);
    }
}