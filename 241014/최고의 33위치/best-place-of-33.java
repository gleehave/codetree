import java.io.*;
import java.util.*;

public class Main {

    static int MAX;
    static int N;
    static int[][] map;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0; i<N; i++){
            String[] data = br.readLine().split(" ");
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(data[j]);
            }
        } // map 입력

        MAX = Integer.MIN_VALUE;
        for(int i=0; i<=N-3; i++){
            for(int j=0; j<=N-3; j++){
          
                int count = 0;
                for(int r=i; r<i+3; r++){
                    for(int c=j; c<j+3; c++){
                        if (map[r][c] == 1) count++;
                    }
                }
                MAX = Math.max(MAX, count);
            }
        }
        System.out.println(MAX);
    }
}