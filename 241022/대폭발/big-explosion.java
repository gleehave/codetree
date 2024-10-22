/*

- 현재 grid의 폭탄이 존재하는 위치에서 4방향을 본다.
    - 4방향은 2^(t-1) 띄워서 봐야한다.
    - 격자 안에있고 + 비어있으면
        - 폭탄 생김
    - 이미 grid에 폭탄이 있다면 생기지 않는다.


*/


import java.io.*;
import java.util.*;

public class Main {

    static int n, m, r, c;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;

        int time = 0;
        grid = new int[n][n];
        visited = new boolean[n][n];

        grid[r][c] = 1;
        visited[r][c] = true;

        while(time++ < m){
            int distance = (int) Math.pow(2, time-1);
            for(int r=0; r<n; r++){
                for(int c=0; c<n; c++){
                    
                    if (grid[r][c] == 1 && visited[r][c]){ 
                        for(int d=0; d<4; d++){

                            int nextR = r + dr[d] * distance;
                            int nextC = c + dc[d] * distance;

                            if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= n) continue;
                            if (grid[nextR][nextC] == 0){
                                grid[nextR][nextC] = 1;
                            }
                        }
                    }
                }
            }

            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if (grid[i][j] == 1 && !visited[i][j]){
                        visited[i][j] = true;
                    }
                }
            }
        }

        int count = 0;
        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                if (grid[r][c] == 1) count++; 
            }
        }
        System.out.println(count);
    }
}