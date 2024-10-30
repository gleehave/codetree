/*

상하좌우로 인접한 같은 숫자는 1개의 블럭이 된다.
4개 이상은 터진다.

터지는 블럭, 최대 블럭 크기
*/


import java.io.*;
import java.util.*;

public class Main {
    
    static int n, count, max;
    static int[][] grid;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};    

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        
        grid = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        } // grid 입력

        max = Integer.MIN_VALUE;
        int bomb = 0;
        visited = new boolean[n][n];
        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                // System.out.println(solve(r, c, grid[r][c], 1));
                count = 1;
                solve(r, c, grid[r][c]);
                if (count >= 4){
                    bomb++;
                }
                max = Math.max(max, count);                
            }
        }

        System.out.println(bomb+" "+max);
    }

    public static void solve(int r, int c, int num){
        visited[r][c] = true;

        for(int d=0; d<4; d++){
            int nextR = r + dr[d];
            int nextC = c + dc[d];

            if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= n) continue;
            if (visited[nextR][nextC]) continue;
            if (num == grid[nextR][nextC]){
                count++;
                solve(nextR, nextC, num);
            }
        }
    }
}