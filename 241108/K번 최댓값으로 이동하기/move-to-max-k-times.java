import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[][] grid;
    static int[] dr = {-1, 1, 0, 0}; // 상하좌우 방향
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 격자 크기와 이동 횟수 입력
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 시작 위치 입력
        st = new StringTokenizer(br.readLine());
        int startRow = Integer.parseInt(st.nextToken()) - 1;
        int startCol = Integer.parseInt(st.nextToken()) - 1;

        // k번 이동을 실행하고 결과 위치를 출력
        int[] result = move(startRow, startCol, k);
        System.out.println((result[0] + 1) + " " + (result[1] + 1)); // 1-based index로 변환하여 출력
    }

    public static int[] move(int row, int col, int moves) {
        int currentRow = row;
        int currentCol = col;

        for (int i = 0; i < moves; i++) {
            int currentValue = grid[currentRow][currentCol];

            // 우선순위 큐를 사용하여 이동할 위치 탐색
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
                if (a[2] != b[2]) return b[2] - a[2]; // 숫자 크기 내림차순
                if (a[0] != b[0]) return a[0] - b[0]; // 행 오름차순
                return a[1] - b[1]; // 열 오름차순
            });

            // 인접한 위치 탐색하여 조건에 맞는 위치를 우선순위 큐에 추가
            for (int d = 0; d < 4; d++) {
                int newRow = currentRow + dr[d];
                int newCol = currentCol + dc[d];

                // 격자 범위 내에 있고, 현재 위치 숫자보다 작은 경우만 추가
                if (newRow >= 0 && newCol >= 0 && newRow < n && newCol < n) {
                    if (grid[newRow][newCol] < currentValue) {
                        pq.offer(new int[]{newRow, newCol, grid[newRow][newCol]});
                    }
                }
            }

            // 이동할 위치가 없으면 종료
            if (pq.isEmpty()) break;

            // 우선순위 큐에서 가장 우선순위가 높은 위치로 이동
            int[] next = pq.poll();
            currentRow = next[0];
            currentCol = next[1];
        }

        // 최종 위치 반환
        return new int[]{currentRow-1, currentCol-1};
    }
}