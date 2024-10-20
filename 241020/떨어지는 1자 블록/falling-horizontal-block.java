import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static int[][] grid;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken())-1;

        grid = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        } // grid 입력

        boolean isStop = false;
        for(int r=0; r<n-1; r++){ // 첫 행은 비워져있음. 바닥으로 떨어진다.
            for(int c=k; c<k+m; c++){
                if (grid[r+1][c] == 1){
                    isStop = true;
                    break;
                }
            }

            if (isStop){
                for(int i=k; i<k+m; i++){
                    grid[r][i] = 1;
                }
                break;
            }
        }

        if (!isStop){
            for(int j=k; j<k+m; j++){
                grid[n-1][j] = 1;
            }
        }

        for(int a=0; a<n; a++){
            for(int b=0; b<n; b++){
                System.out.print(grid[a][b]+" ");
            }
            System.out.println();
        }
    }
}