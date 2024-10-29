import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int result;
    static int[][] grid;
    static boolean[][] visited;

    static int[] dr = {1, 0};
    static int[] dc = {0, 1};    

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n][m];
        grid = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        } // grid 입력

        result = 0;
        solve(0, 0);
        System.out.println(result);
    }

    public static void solve(int r, int c){
        visited[0][0] = true;

        for(int d=0; d<2; d++){
            int nextR = r + dr[d];
            int nextC = c + dc[d];

            if (nextR == n-1 && nextC == m-1){
                result = 1;
                return;
            }

            // 격자 바깥으로는 이동 못한다.
            if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= m) continue;

            // 이미 방문했으면 제외
            if (visited[nextR][nextC]) return;

            // 뱀이 있으면 제외
            if (grid[nextR][nextC] == 0) continue;

            solve(nextR, nextC);
        }        
    }
}