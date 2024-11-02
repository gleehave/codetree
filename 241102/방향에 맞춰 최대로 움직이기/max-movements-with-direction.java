/*
현재 숫자, 방향이 격자에서 주어진다.
방향으로 현재 숫자보다 크면 이동할 수 있다.

최대 이동횟수가 궁금하다.

예시에서 생각하면 
6을 이동한다. 이동하지 않는다.
7을 이동한다. 이동하지 않는다.
*/

import java.io.*;
import java.util.*; 

public class Main {

    static int move;
    static int n, currR, currC;
    static int[][][] grid;
    static boolean[][] visited;

    static int[] dr = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 0, 1, 1, 1, 0, -1, -1, -1}; // 현재(0,0) + 8방향 = 총 9개

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        
        grid = new int[n][n][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                grid[i][j][0] = Integer.parseInt(st.nextToken());
            }
        } // 숫자 입력

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                grid[i][j][1] = Integer.parseInt(st.nextToken());
            }
        } // 방향 입력

        st = new StringTokenizer(br.readLine());
        currR = Integer.parseInt(st.nextToken())-1;
        currC = Integer.parseInt(st.nextToken())-1;

        move = Integer.MIN_VALUE;
        visited = new boolean[n][n];
        visited[currR][currC] = true;

        solve(currR, currC, 0); // 위치, move횟수
        System.out.println(move);
    } // main

    
    public static void solve(int r, int c, int count){

        for(int i=1; i<=n; i++){ // 최대 n 번 이동할 수 있음.
            int nextR = r + dr[grid[r][c][1]] * i;
            int nextC = c + dc[grid[r][c][1]] * i;

            if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= n) continue;
            if (grid[nextR][nextC][0] < grid[r][c][0]) continue;

            visited[nextR][nextC] = true;
            solve(nextR, nextC, count+1);
            visited[nextR][nextC] = false;
        }

        move = Math.max(move, count);
    }   
}