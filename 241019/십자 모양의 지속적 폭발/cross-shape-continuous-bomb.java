/*
while(){
    1. 폭탄이 내려간다.
        - grid[r][c] != 0 이면 폭발 시작
    2. 범위 내에 들어오는 것은 0으로 처리
    3. 값들을 중력방향으로 움직인다.
}
*/

import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] grid;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // map 입력
        
        while(m-- > 0){
            st = new StringTokenizer(br.readLine());
            int drop = Integer.parseInt(st.nextToken()) - 1;

            for(int r=0; r<n; r++){
                if (grid[r][drop] == 0) continue;
                bomb(r, drop);
                break;
            }
            move();
        }

        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                System.out.print(grid[r][c]+" ");
            }
            System.out.println();
        }
    } // main

    public static void bomb(int r, int c){
        for(int d=0; d<4; d++){
            int nr = r;
            int nc = c;
            for(int t=0; t<grid[r][c]-1; t++){ // 폭탄 값 만큼 시도
                nr += dr[d];
                nc += dc[d];
                if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
                grid[nr][nc] = 0;
            }
        }
        grid[r][c] = 0; // 폭탄 처음 자리도 0으로 변경
    }

    public static void move(){
        int index = n-1;
        int[][] temp = new int[n][n];

        for(int c=0; c<n; c++){
            for(int r=n-1; r >=0; r--){ // grid의 아래서부터 위로 올라간다.
                if (grid[r][c] == 0) continue;
                temp[index--][c] = grid[r][c];
            }
            index = n-1;
        }

        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                grid[r][c] = temp[r][c];
            }
        }
    }
}