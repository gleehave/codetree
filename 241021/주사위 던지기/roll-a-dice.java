/*
밑면 기준
{
    {0, 0, 0, 0, 0}, // 버리는 자리
    {2, 5, 3, 4, 6}, // 밑면 1
    {1, 6, 4, 3, 5},
    {6, 1, 5, 2, 4},
    {6, 1, 2, 5, 3},
    {6, 1, 4, 3, 2},
    {2, 5, 4, 3, 1}, // 밑면 6, 상하좌우
}
*/

import java.util.*;
import java.io.*;

class Dice{
    int r, c;
    int now;
    static int[] bottom = {
        {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
        {{2,5,3,4}, {4,3,2,5}, {5,2,4,3}, {3,4,5,2}},
        {{1,6,4,3}, {3,4,1,6}, {6,1,3,4}, {4,3,6,1}},
        {{2,5,6,1}, {1,6,2,5}, {5,2,1,6}, {6,1,5,2}},
        {{2,5,1,6}, {6,1,2,5}, {5,2,6,1}, {1,6,5,2}},
        {{6,1,4,3}, {3,4,6,1}, {1,6,3,4}, {4,3,1,6}},
        {{2,5,4,3}, {3,4,2,5}, {5,2,3,4}, {4,3,5,2}},
    }

    public Dice(int r, int c){
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int n, m, r, c;
    static int[][] grid;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1}; // 상하좌우

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;

        grid = new int[n][n];
        Dice dice = new Dice(r, c);
        grid[dice.r][dice.c] = 6; // 초기값
        dice.now = 6; // 초기값
        
        String[] direction = br.readLine().split(" ");
        for(int i=0; i<direction.length; i++){
            int d = convertDirection(direction[i]);
            int nextR = dice.r + dr[d];
            int nextC = dice.c + dc[d];

            if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= n){
                continue;
            }

            grid[nextR][nextC] = dice.bottom[dice.now][d]

            
        }


    }

    public static int convertDirection(String dir){
        if (dir.equals("U")) return 0;
        else if (dir.equals("D")) return 1;
        else if (dir.equals("L")) return 2;
        else return 3;
    }
}