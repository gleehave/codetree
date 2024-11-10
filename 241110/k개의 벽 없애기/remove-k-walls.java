import java.io.*;
import java.util.*;

public class Main {
    static int n, k, min;
    static int startR, startC, endR, endC;
    static int[][] grid;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static boolean[] deleted;
    static List<Integer> deletedWall = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        } // grid입력

        st = new StringTokenizer(br.readLine());
        startR = Integer.parseInt(st.nextToken())-1;
        startC = Integer.parseInt(st.nextToken())-1;
        
        st = new StringTokenizer(br.readLine());
        endR = Integer.parseInt(st.nextToken())-1;
        endC = Integer.parseInt(st.nextToken())-1;

        min = Integer.MAX_VALUE;
        simulate(0, 0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    public static void simulate(int depth, int cnt){
        if (depth == n * n){
            if (cnt != k) return;
            min = Math.min(min, bfs());
            return;
        }

        int r = depth / n;
        int c = depth % n;
        if (grid[r][c] == 1){
            deletedWall.add(depth);
            simulate(depth+1, cnt+1);

            deletedWall.remove(deletedWall.size()-1);
            simulate(depth+1, cnt);
        } else {
            simulate(depth+1, cnt);
        }
    }

    public static int bfs(){
        deletWall();
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{startR, startC, 0});
        visited[startR][startC] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            for(int d=0; d<4; d++){
                int nextR = cur[0] + dr[d];
                int nextC = cur[1] + dc[d];

                if (nextR == endR && nextC == endC){
                    return cur[2]+1;
                }

                if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= n) continue;
                if (visited[nextR][nextC]) continue;
                if (grid[nextR][nextC] == 1) continue;

                visited[nextR][nextC] = true;
                queue.offer(new int[]{nextR, nextC, cur[2]+1});
            }
        }
        initGrid();
        return Integer.MAX_VALUE;
    } // bfs

    public static void deletWall(){
        for(int index : deletedWall){
            int r = index / n;
            int c = index % n;
            grid[r][c] = 0;
        }
    }

    public static void initGrid(){
        for(int index : deletedWall){
            int r = index / n;
            int c = index % n;
            grid[r][c] = 1;
        }
    }
}