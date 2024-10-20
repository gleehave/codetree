/*
필요한 함수
while(K번 반복)
1. M 이상 연속된 폭탄 터뜨리기
2. 아래로 내리기
3. 90도 회전
*/

import java.io.*;
import java.util.*;

public class Main {

    static int n, m, k;
    static int[][] grid;

    public static void main(String[] args) throws Exception{
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); 
        m = Integer.parseInt(st.nextToken()); 
        k = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        } // grid 입력

        while(k-- >= 0){

            for(int i=0; i<n; i++){
                bomb();
                down();
            }
            
            rotate();
            down();
        }

        int total = 0;
        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                if (grid[r][c] != 0) total++;
            }
        }
        System.out.println(total);
    }

    public static void bomb(){
        for(int c=0; c<n; c++){
            int count = 1;
            for(int r=1; r<n; r++){
                if (grid[r][c] == grid[r-1][c]) count++;
                else {
                    if (count >= m){
                        for(int z=r-1; z >= r-count; z--) grid[z][c] = 0;
                    }
                    count = 1;
                }
            }

            if (count >= m){
                for(int z = n-1; z>=n-count; z--){
                    grid[z][c] = 0;
                }
            }
        }
    }

    public static void down(){
        int[][] temp = new int[n][n];

        for(int c=0; c<n; c++){
            int index = n-1;
            for(int r=n-1; r>=0; r--){ // 아래서부터 올라온다.
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

    public static void rotate(){
        int[][] temp = new int[n][n];

        for(int c=0; c<n; c++){
            for(int r=0; r<n; r++){
                temp[r][c] = grid[n-1-c][r];
            }
        }

        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                grid[r][c] = temp[r][c];
            }
        }
    }
}