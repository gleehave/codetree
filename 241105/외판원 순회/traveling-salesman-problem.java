import java.io.*;
import java.util.*;

public class Main {
    static int n, min;
    static int[][] grid;
    static boolean[] visited;
    static List<Integer> route = new ArrayList<>();

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
        } // grid

        min = Integer.MAX_VALUE;
        visited = new boolean[n];
        solve(0);
        System.out.println(min);
    }

    public static void solve(int depth){
        if (depth == n){
            int sum = 0;

            for(int i=1; i<route.size(); i++){
                sum += grid[route.get(i-1)][route.get(i)];
            }
            sum += grid[route.get(route.size()-1)][route.get(0)];
            min = Math.min(min, sum);
            return;
        }

        for(int i=0; i<n; i++){
            if (visited[i]) continue; // 이미 방문
            if (grid[depth][i] == 0) continue; // 이동불가

            visited[i] = true;
            route.add(i);

            solve(depth+1);

            route.remove(route.size()-1);
            visited[i] = false;
        }
    }
}