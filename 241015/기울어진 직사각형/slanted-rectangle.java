import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int total;
    static int[][] map;

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
                rectangle(r, c, map[r][c], 0);
            }
        }
        System.out.println(total); 
    }

    private static void rectangle(int r, int c, int score, int dir){
        if (dir == 5){
            total = Math.max(total, score);
            return;
        }

        int nextR = r;
        int nextC = c;
        switch(dir){
            case 0:
                nextR -= 1;
                nextC += 1;        
                break;
            case 1:
                nextR -= 1;
                nextC -= 1;
                break;    
            case 2:
                nextR += 1;
                nextC -= 1;
                break;
            case 3:
                nextR += 1;
                nextC += 1;
                break;
        }
        if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= N){
            dir++;
            return;
        }

        rectangle(nextR, nextC, score + map[nextR][nextC], dir++);
    }
}