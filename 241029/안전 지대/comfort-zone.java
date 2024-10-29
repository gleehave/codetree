import java.io.*;
import java.util.*;

public class Main {

    static int finalArea, final_k;
    static int n, m;
    static int[][] grid;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int max_k = Integer.MIN_VALUE;
        grid = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
                max_k = Math.max(max_k, grid[i][j]);
            }
        } // grid 입력

        final_k = max_k;
        finalArea = Integer.MIN_VALUE;
        
        while(max_k-- > 0){
            visited = new boolean[n][m];
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if (grid[i][j] <= max_k) visited[i][j] = true; // 경계선이 된다.
                }
            }

            int safeArea = 0;
            for(int r=0; r<n; r++){
                for(int c=0; c<m; c++){
                    if (visited[r][c]) continue;
                    solve(r, c);
                    safeArea++;
                }
            }

            if (safeArea >= finalArea){
                finalArea = safeArea;
                final_k = max_k;
            }
        }

        System.out.println(final_k+" "+finalArea);

    } // main

    public static void solve(int r, int c){
        visited[r][c] = true;

        for(int d=0; d<4; d++){
            int nextR = r + dr[d];
            int nextC = c + dc[d];

            if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= m) continue;
            if (visited[nextR][nextC]) continue;
            solve(nextR, nextC);
        }

    }
}