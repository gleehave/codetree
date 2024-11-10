import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        } // grid 입력

        System.out.println(simulate());
    }

    public static int simulate(){
        queue.offer(new int[]{0, 0, 0});
        visited[0][0] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            
            for(int d=0; d<4; d++){
                int nextR = cur[0] + dr[d];
                int nextC = cur[1] + dc[d];

                if (nextR == n-1 && nextC == m-1){
                    return cur[2] + 1;
                }

                if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= m) continue;
                if (visited[nextR][nextC]) continue;
                if (grid[nextR][nextC] == 0) continue; // 뱀 존재

                visited[nextR][nextC] = true;
                queue.offer(new int[]{nextR, nextC, cur[2]+1});
            }
        }
        return -1;
    }
}