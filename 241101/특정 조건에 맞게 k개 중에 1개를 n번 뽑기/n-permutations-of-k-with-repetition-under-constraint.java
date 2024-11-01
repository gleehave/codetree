/*
기본적으로 순열이다. k=3일 때, 1 2 3 4
1 1 1 1
1 1 1 2
1 1 1 3
1 1 1 4

depth == N 일 때, 종료이다.

for(1 ~ K)
    add(i)
    if (연속이면){
        remove(last)
        add(i+1)
    }
    solve(depth+1)
    remove(last)


*/

import java.io.*;
import java.util.*;

public class Main {
    static int k, n;
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        solve(0);
    }

    public static void solve(int depth){
        if (depth == n){
            
            for(int j=0; j<result.size(); j++){
                System.out.print(result.get(j)+" ");
            }
            System.out.println();

            return;
        }

        for(int i=1; i<=k; i++){
            result.add(i);
            int last = result.size()-1;
            if (result.size() >= 3){
                if ((result.get(last) == result.get(last-1)) && (result.get(last) == result.get(last-2))){
                    result.remove(last);
                    continue;
                }
            }
            solve(depth+1);
            result.remove(last);
        }
    }
}