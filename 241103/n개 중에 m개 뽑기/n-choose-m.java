/*
2 3 1 1 4 에서 2는 1칸, 2칸 까지 가능한 것.
*/

import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        solve(0, 0);
    }

    public static void solve(int depth, int num){
        if (depth == m){
            for(int i=0; i<result.size(); i++){
                System.out.print(result.get(i)+" ");
            }
            System.out.println();
            return;
        }

        for(int i=num+1; i<=n; i++){
            result.add(i);
            solve(depth+1, i);
            result.remove(result.size()-1);
        }
    }
}