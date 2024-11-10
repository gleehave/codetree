import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] grid;
    static int[][] step;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        visited = new boolean[n][n];
        grid = new int[n][n];
        step = new int[n][n];
        
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
                
                if (grid[i][j] == 2) {  // 상한 귤인 경우
                    queue.offer(new int[]{i, j, 0});  // 초기 위치와 시간 0을 함께 저장
                    visited[i][j] = true;
                } 
                if (grid[i][j] == 0) step[i][j] = -1;  // 빈 칸일 경우 -1로 설정
            }
        }

        solve();

        for(int r = 0; r < n; r++){
            for(int c = 0; c < n; c++){
                System.out.print(step[r][c] + " ");
            }
            System.out.println();
        }
    }

    public static void solve(){
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int curR = cur[0];
            int curC = cur[1];
            int time = cur[2];
            step[curR][curC] = time;

            for(int d = 0; d < 4; d++){
                int nextR = curR + dr[d];
                int nextC = curC + dc[d];

                if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= n) continue;
                if (visited[nextR][nextC] || grid[nextR][nextC] == 0 || grid[nextR][nextC] == 2) continue;

                visited[nextR][nextC] = true;
                queue.offer(new int[]{nextR, nextC, time + 1});  // 시간 증가
            }
        }

        // 초기 상태에서 주변에 상한 귤이 없어 영원히 상할 수 없는 귤을 -2로 표시
        for(int r = 0; r < n; r++){
            for(int c = 0; c < n; c++){
                if (grid[r][c] == 1 && step[r][c] == 0) {  // 상할 수 없는 귤
                    boolean isIsolated = true;
                    for(int d = 0; d < 4; d++){
                        int nextR = r + dr[d];
                        int nextC = c + dc[d];

                        if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= n) continue;
                        if (grid[nextR][nextC] != 0) {
                            isIsolated = false;
                            break;
                        }
                    }
                    if (isIsolated) step[r][c] = -2;
                }
            }
        }
    }
}