import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[][] grid;
    static int[][] step;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        visited = new boolean[n][n];
        grid = new int[n][n];
        step = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == 2){
                    queue.offer(new int[]{i, j, 0});                    
                    visited[i][j] = true;
                } 
                if (grid[i][j] == 0) step[i][j] = -1;
            }
        } // grid 입력

        solve();

        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                if (grid[r][c] == 1){
                    boolean check = false;
                    for(int d=0; d<4; d++){
                        int nextR = r + dr[d];
                        int nextC = c + dc[d];
                        
                        if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= n) continue;
                        if (grid[nextR][nextC] != 0) check = true;
                        if (check) break;
                    }
                    if (!check) step[r][c] = -2;
                }
            }
        }

        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                System.out.print(step[r][c]+" ");
            }
            System.out.println();
        }
    }

    public static void solve(){

        while(!queue.isEmpty()){
            int curSize = queue.size();

            for(int i=0; i<curSize; i++){
                int[] cur = queue.poll();
                for(int d=0; d<4; d++){
                    int nextR = cur[0] + dr[d];
                    int nextC = cur[1] + dc[d];

                    if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= n) continue;
                    if (visited[nextR][nextC]) continue;
                    if (grid[nextR][nextC] == 0 || grid[nextR][nextC] == 2) continue;

                    visited[nextR][nextC] = true;
                    queue.offer(new int[]{nextR, nextC, cur[2] + 1});
                    step[nextR][nextC] = cur[2]+1;
                }
            }
        }
    }
}