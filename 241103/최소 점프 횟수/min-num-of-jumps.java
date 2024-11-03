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

        min = -1;
        solve(0, 0);
        System.out.println(min);
    }

    public static void solve(int current, int move){
        if (current == n){
            min = Math.min(min, move);
            return;
        }

        for(int i=current; i<=current+jump[current]; i++){
            if (current > n){
                return;
            }
            System.out.println(i);
            // solve(i, move+1);
        }
        
    }
}