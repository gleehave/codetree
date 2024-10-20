/*
모든 격자판 위치에서 해봐야 알 수 있다.
1. r, c에서 터뜨린다.
2. down() 시킨다.
3. 2쌍씩 세로 탐색, 가로 탐색해서 counting
*/

import java.util.*;
import java.io.*;


public class Main {
    static int n, max;
    static int[][] grid;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        grid = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        max = Integer.MIN_VALUE;
        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                int[][] searchMap = down(bomb(r, c));
                search(searchMap);
            }
        }
        System.out.println(max);
    }

    public static void search(int[][] map){
        int count = 0;

        // 가로
        for(int r=0; r<n; r++){
            for(int c=0; c<n-1; c++){
                if (map[r][c] == 0) continue;
                if (map[r][c] == map[r][c+1]) count++;
            }
        }

        // 세로
        for(int c=0; c<n; c++){
            for(int r=0; r<n-1; r++){
                if (map[r][c] == 0) continue;
                if (map[r][c] == map[r+1][c]) count++;
            }
        }
        max = Math.max(max, count);
    }

    public static int[][] bomb(int r, int c){
        int[][] temp = new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                temp[i][j] = grid[i][j];
            }
        } // 복사본 생성

        for(int d=0; d<4; d++){
            int nr = r;
            int nc = c;
            for(int bom=0; bom<grid[r][c]-1; bom++){
                nr += dr[d];
                nc += dc[d];
                if (nr < 0 || nc <0 || nr>=n || nc>=n) continue;
                temp[nr][nc] = 0;
            }
            temp[r][c] = 0;
        }
        return temp;
    }

    public static int[][] down(int[][] map){
        int[][] temp = new int[n][n];

        for(int c=0; c<n; c++){
            int index = n-1;
            for(int r=n-1; r>=0; r--){
                if (map[r][c] == 0) continue;
                temp[index--][c] = map[r][c];
            }
        }

        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                map[r][c] = temp[r][c];
            }
        }
        return map;
    }
}