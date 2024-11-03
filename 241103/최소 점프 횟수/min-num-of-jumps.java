package CT.simulation;

/*
2 3 1 1 4 에서 2는 1칸, 2칸 까지 가능한 것.
*/

import java.io.*;
import java.util.*;

public class Main {
    static int n, min;
    static int[] jump;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        jump = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            jump[i] = Integer.parseInt(st.nextToken());
        }

        min = Integer.MAX_VALUE;
        solve(0, 0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    public static void solve(int current, int move){
        if (current == n-1){
            min = Math.min(min, move);
            return;
        }

        for(int i=current+1; i<=current+jump[current]; i++){
            if (i >= n){
                continue;
            }
            solve(i, move+1);
        }
        
    }
}