import java.io.*;
import java.util.*;

class Point{
    int r, c;

    public Point(int r, int c){
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int n, r, c;
    static int[][] grid;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1}; // 상하좌우

    static List<Integer> path = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;

        grid = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        } // grid 입력

        Point point = new Point(r, c);
        path.add(grid[r][c]);
        while(true){
            if (!moreMove(point)) break;

            for(int d=0; d<4; d++){
                int nr = point.r + dr[d];
                int nc = point.c + dc[d];
                
                if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
                if (grid[nr][nc] > grid[point.r][point.c]){
                    path.add(grid[nr][nc]);
                    point.r = nr;
                    point.c = nc;
                    break;
                }
            }
        }
        for(int n:path){
            System.out.print(n+" ");
        }
    }

    public static boolean moreMove(Point point){    
        for(int d=0; d<4; d++){
            int nr = point.r + dr[d];
            int nc = point.c + dc[d];

            if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
            if (grid[nr][nc] > grid[point.r][point.c]) return true;
        }
        return false;
    }
}