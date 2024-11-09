import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] grid;
    static boolean[][] visited;

    static Queue<int[]> queue = new LinkedList<>();
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
                if (i==0 || j==0 || i==n-1 || j == m-1){
                    visited[i][j] = true;
                    queue.offer(new int[]{i, j});
                } 
            }
        } // grid 

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if (i==0 || j==0 || i==n-1 || j == m-1) continue;
                if (grid[i][j] == 1) continue;

                for(int d=0; d<4; d++){
                    int nextR = i + dr[d];
                    int nextC = j + dc[d];

                    if (grid[nextR][nextC] == 0){
                        visited[i][j] = true;
                        queue.offer(new int[]{i, j});
                        break;
                    } 
                }
            }
        }
        simulate();
    }

    public static void simulate(){
        int time = -1;
        int lastSize = 0;

        while(!queue.isEmpty()){
            time++;
            int currSize = queue.size();
            lastSize = currSize;

            for(int i=0; i<currSize; i++){
                int[] cur = queue.poll();

                for(int d=0; d<4; d++){
                    int nextR = cur[0] + dr[d];
                    int nextC = cur[1] + dc[d];

                    if (nextR < 0 || nextC < 0 || nextR >=n || nextC >= m){
                        continue;
                    }
                    if (visited[nextR][nextC]){
                        continue;
                    }

                    grid[nextR][nextC] = 0;
                    visited[nextR][nextC] = true;
                    queue.offer(new int[]{nextR, nextC});
                }
            }

            for(int r=0; r<n; r++){
                for(int c=0; c<m; c++){
                   if (r==0 || c==0 || r==n-1 || c == m-1) continue;
                   if (grid[r][c] == 1) continue;

                   for(int d=0; d<4; d++){
                       int nextR = r + dr[d];
                       int nextC = c + dc[d];

                       if (visited[r][c]) continue;
                        if (grid[nextR][nextC] == 0){
                            visited[r][c] = true;
                            queue.offer(new int[]{r, c});
                            break;
                        } 
                    }
                }
            }
        }
        System.out.println(time+" "+lastSize);
    }
}