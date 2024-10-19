import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] grid;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1}; // 상하좌우

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken())-1;
        int c = Integer.parseInt(st.nextToken())-1;

        for(int d=0; d<4; d++){
            int nr = r;
            int nc = c;
            for(int move=0; move<grid[r][c]-1; move++){
                nr += dr[d];
                nc += dc[d];
                if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
                grid[nr][nc] = 0;
            }
        }
        grid[r][c] = 0;

        for(int j=0; j<n; j++){
            Stack<Integer> stack = new Stack<>();
            for(int i=0; i<n; i++){
                if (grid[i][j] == 0) continue;
                stack.push(grid[i][j]);
                grid[i][j] = 0;
            }

            for(int i=n-1; i>=0; i--){
                if (!stack.isEmpty()){
                    grid[i][j] = stack.pop();
                }
            }
         }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
    }
}