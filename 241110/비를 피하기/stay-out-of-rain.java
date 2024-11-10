import java.io.*;
import java.util.*;

public class Main {
    static int n, h, m;
    static int[][] grid;
    static int[][] step;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        step = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        } // grid

        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                if (grid[r][c] == 2){
                    step[r][c] = bfs(r, c);
                }
            }
        }

        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                System.out.print(step[r][c] +" ");
            }
            System.out.println();
        }
    }

    public static int bfs(int r, int c){
        init();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c, 0}); 
        visited[r][c] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            for(int d=0; d<4; d++){
                int nextR = cur[0] + dr[d];
                int nextC = cur[1] + dc[d];

                if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= n) continue;
                if (visited[nextR][nextC]) continue;
                if (grid[nextR][nextC] == 1) continue;

                if (grid[nextR][nextC] == 3){
                    return cur[2] + 1;
                }

                visited[nextR][nextC] = true;
                queue.offer(new int[]{nextR, nextC, cur[2]+1});
            }
        }
        return -1;

    }

    public static void init(){
        visited = new boolean[n][n];

        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                visited[r][c] = false;
            }
        }
    }
}