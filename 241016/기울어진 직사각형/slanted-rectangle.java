import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int total;
    static int[][] map;
    static int[] dr = {-1, -1, 1, 1};
    static int[] dc = {1, 1, -1, -1};

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } // map 입력

        total = 0;
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                rectangle(r, c, r, c, map[r][c], 0);
            }
        }
        System.out.println(total); 
    }

    private static void rectangle(int startR, int startC, int nextR, int nextC, int score, int dir){
        if (dir == 4) return;
        if (nextR == startR && nextC == startC && dir == 3){
            total = Math.max(total, score);
            return;
        }

        nextR += dr[dir];
        nextC += dc[dir];
        
        if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= N) return;

        rectangle(startR, startC, nextR, nextC, score+map[nextR][nextC], dir);
        rectangle(startR, startC, nextR, nextC, score, dir+1);
    }
}