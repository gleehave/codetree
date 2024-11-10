import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int startR, startC, endR, endC;
    static boolean[][] visited;
    static int[] dr = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dc = {-2, -1, 1, 2, -2, -1, 1, 2};
    static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        visited = new boolean[n][n];

        st = new StringTokenizer(br.readLine());
        startR = Integer.parseInt(st.nextToken())-1;
        startC = Integer.parseInt(st.nextToken())-1;
        endR = Integer.parseInt(st.nextToken())-1;
        endC = Integer.parseInt(st.nextToken())-1;

        if (n == 1) System.out.println(0);
        else System.out.println(simulate());   
    }

    public static int simulate(){
        queue.offer(new int[]{startR, startC, 0});
        visited[startR][startC] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            for(int d=0; d<dr.length; d++){
                int nextR = cur[0] + dr[d];
                int nextC = cur[1] + dc[d];

                if (nextR == endR && nextC == endC){
                    return cur[2]+1;
                }

                if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= n) continue;
                if (visited[nextR][nextC]) continue;

                visited[nextR][nextC] = true;
                queue.offer(new int[]{nextR, nextC, cur[2]+1});
            }
        }
        return -1;
    }
}