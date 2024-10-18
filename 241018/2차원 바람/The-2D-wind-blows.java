import java.io.*;
import java.util.*;

public class Main {
    static int n, m, q;
    static int[][] grid;
    static int r1, c1, r2, c2;
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(q-- > 0){
            st = new StringTokenizer(br.readLine());
            r1 = Integer.parseInt(st.nextToken())-1;
            c1 = Integer.parseInt(st.nextToken())-1;
            r2 = Integer.parseInt(st.nextToken())-1;
            c2 = Integer.parseInt(st.nextToken())-1;

            rotate(r1, c1, r2, c2);
            average(r1, c1, r2, c2);
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }

    } // main

    public static void rotate(int r1, int c1, int r2, int c2){
        int index = 0;
        int[] arr = new int[2 * Math.abs(c2 - c1 + 1) + 2*(Math.abs(r2-r1 + 1)-2)];
        for(int i=c1; i<=c2 ; i++) arr[index++] = grid[r1][i]; 
        for(int i=r1+1; i<=r2 ; i++) arr[index++] = grid[i][c2];
        for(int i=c2-1; i>=c1 ; i--) arr[index++] = grid[r2][i]; 
        for(int i=r2-1; i> r1 ;i--) arr[index++] = grid[i][c1];

        int temp = arr[arr.length-1];
        for(int i=arr.length-1; i>0; i--){
            arr[i] = arr[i-1];
        }
        arr[0] = temp;

        index = 0;
        for(int i=c1; i<=c2 ; i++) grid[r1][i] = arr[index++]; 
        for(int i=r1+1; i<=r2; i++) grid[i][c2] = arr[index++];
        for(int i=c2-1; i>=c1; i--) grid[r2][i] = arr[index++];
        for(int i=r2-1; i>r1; i--) grid[i][c1] = arr[index++];
    }

    public static void average(int r1, int c1, int r2, int c2){

        int[][] map = new int[n][m];
        
        for(int i=r1; i<=r2; i++){
            for(int j=c1; j<=c2; j++){
                int sum = 0;
                int nr, nc;
                int count = 0;
                for(int d=0; d<4; d++){
                    nr = i + dr[d];
                    nc = j + dc[d];

                    if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
                    sum += grid[nr][nc];
                    count++;
                }

                sum += grid[i][j];
                map[i][j] = sum / (count+1);
            }
        }

        for(int i=r1; i<=r2; i++){
            for(int j=c1; j<=c2; j++){
                grid[i][j] = map[i][j];
            }
        }
    }
}