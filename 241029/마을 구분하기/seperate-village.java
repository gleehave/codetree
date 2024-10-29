import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] grid;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        visited = new boolean[n][n];
        grid=new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        } // grid 입력

        int sector = -1;
        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                if (grid[r][c] == 1 && !visited[r][c]){
                    solve(r, c, sector--);
                }
            }
        }

        System.out.println(Math.abs(sector)-1);
        int[] counting = new int[Math.abs(sector)-1];

        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                if (grid[r][c] == 0) continue;
                counting[Math.abs(grid[r][c]) - 1]++;
            }
        }
        Arrays.sort(counting);
        for(int count: counting){
            System.out.println(count);
        }
    }

    public static void solve(int r, int c, int sector){
        visited[r][c] = true;
        grid[r][c] = sector;

        for(int d=0; d<4; d++){
            int nextR = r + dr[d];
            int nextC = c + dc[d];

            if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= n) continue;
            if (visited[nextR][nextC]) continue;
            if (grid[nextR][nextC] == 0) continue;

            solve(nextR, nextC, sector);
        }
    }
}