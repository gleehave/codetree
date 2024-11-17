import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] grid;
    static int[][] memo;

    static int[][] min;
    static int[][] max;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        min = new int[n][n];
        max = new int[n][n];
        memo = new int[n][n];
        grid = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        } // grid 입력

        min[0][0] = grid[0][0];
        max[0][0] = grid[0][0];

        for(int c=1; c<n; c++){
            min[0][c] = Math.min(min[0][c-1], grid[0][c]);
        }

        for(int r=1; r<n; r++){
            min[r][0] = Math.min(min[r-1][0], grid[r][0]);
        }

        for(int c=1; c<n; c++){
            max[0][c] = Math.max(max[0][c-1], grid[0][c]);
        }

        for(int r=1; r<n; r++){
            max[r][0] = Math.max(max[r-1][0], grid[r][0]);
        }

        for(int r=1; r<n; r++){
            for(int c=1; c<n; c++){
                min[r][c] = Math.min(Math.min(min[r-1][c], min[r][c-1]), grid[r][c]);
                
            }
        }

        for(int r=1; r<n; r++){
            for(int c=1; c<n; c++){
                max[r][c] = Math.max(Math.max(max[r-1][c], max[r][c-1]), grid[r][c]);
            }
        }

        PRINT(min);
        System.out.println();
        PRINT(max);
        
    }

    public static void PRINT(int[][] map){
        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
    }
}