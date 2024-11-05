import java.io.*;
import java.util.*;

public class Main {
    static int n, max;
    static int[][] grid;
    static boolean[] used;
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        grid = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        } // grid 입력

        used = new boolean[n];
        max = Integer.MIN_VALUE;
        solve(0);
        System.out.println(max);
    }

    public static void solve(int depth){
        if (depth == n){ // n개를 골랐다.
            int sum = 0;
            for(int i=0; i<result.size(); i++){
                sum += result.get(i);
            }
            max = Math.max(max, sum);
            return;
        }

        for(int i=0; i<n; i++){
            if (used[i]) continue;
            used[i] = true;
            result.add(grid[depth][i]);

            solve(depth+1);

            used[i] = false;
            result.remove(result.size()-1);
        }
    }
}