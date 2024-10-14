import java.io.*;
import java.util.*;

public class Main {

    static int MAX;
    static int n, m;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        MAX = Integer.MIN_VALUE;
        for(int r=0; r<=n-2; r++){
            for(int c=0; c<=m-2; c++){
                MAX = Math.max(MAX, map[r][c] + map[r][c+1] + map[r+1][c+1]); // ㄱ
                MAX = Math.max(MAX, map[r][c] + map[r+1][c] + map[r+1][c+1]); // ㄴ
                MAX = Math.max(MAX, map[r+1][c] + map[r][c+1] + map[r+1][c+1]); // ㄴ 회전
                MAX = Math.max(MAX, map[r][c] + map[r][c+1] + map[r+1][c]); // ㄱ 회전
                
            }
        }

        for(int r=0; r<=n-3; r++){
            for(int c=0; c<m; c++){
                MAX = Math.max(MAX, map[r][c] + map[r+1][c] + map[r+2][c]);
            }
        }

        for(int r=0; r<n; r++){
            for(int c=0; c<m-3; c++){
                MAX = Math.max(MAX, map[r][c] + map[r][c+1] + map[r][c+2]);                
            }
        }
        System.out.println(MAX);
    }
}