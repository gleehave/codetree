import java.io.*;
import java.util.*;

public class Main {
    static int n, max;
    static int[][] grid;
    static boolean[] selected;
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
        }

        max = Integer.MIN_VALUE;
        selected = new boolean[n];
        solve(0);
        System.out.println(max);
    }

    public static void solve(int depth){
        if (depth == n){
            int num = Integer.MAX_VALUE;
            for(int i=0; i<result.size(); i++){
                num = Math.min(num, result.get(i));
            }

            max = Math.max(num, max);
            
            return;
        }

        for(int i=0; i<n; i++){
            if (selected[i]) continue;
            selected[i] = true;
            result.add(grid[depth][i]);

            solve(depth+1);

            selected[i] = false;
            result.remove(result.size()-1);
        }
    }
}