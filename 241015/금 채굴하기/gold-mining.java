import java.io.*;
import java.util.*;

public class Main {
    static int n, m, total;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        total = 0;
        
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                miningGold(r, c);
            }
        }
        System.out.println(total);
    }

    private static void miningGold(int r, int c){
        
        int K = 0;
        while(K<=n){
            int count = 0;
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if ((Math.abs(r - i) + Math.abs(c - j)) <= K && map[i][j] == 1){
                        count++;
                    }
                }
            }
            int miningCost = (K * K) + (K+1) * (K+1);
            if ((m*count) >= miningCost){
                total = Math.max(total, count);
            }
            
            K++;
        }        
    }
}