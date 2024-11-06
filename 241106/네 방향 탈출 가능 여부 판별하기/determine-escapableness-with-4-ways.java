import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] grid;
    static boolean[][] visited;

    static Queue<int[]> queue;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

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
                if (grid[i][j] == 0) visited[i][j] = true; // 이미 뱀이 있기 때문에 못감.
            }
        } // grid

        bfs();
    }

    public static void bfs(){
        queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            for(int d=0; d<4; d++){
                int nextR = cur[0] + dr[d];
                int nextC = cur[1] + dc[d];

                if (nextR == n-1 && nextC == m-1){
                    System.out.println(1);
                }

                if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= m) continue;
                if (visited[nextR][nextC]) continue;
                if (grid[nextR][nextC] == 0) continue;

                queue.add(new int[]{nextR, nextC});
            }
        }
        System.out.println(0);
    }
}