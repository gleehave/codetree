import java.io.*;
import java.util.*;

public class Main {

    static int n, k, u, d;
    static int[][] grid;
    static boolean[][] visited;
    static int maxCities = 0;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static List<Integer> selectedCities = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());  // 격자 크기
        k = Integer.parseInt(st.nextToken());  // 고를 도시 수
        u = Integer.parseInt(st.nextToken());  // 높이 차이 하한
        d = Integer.parseInt(st.nextToken());  // 높이 차이 상한

        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[n][n];
        selectCities(0, 0);
        System.out.println(maxCities);
    }

    // k개의 도시를 선택하는 조합 생성
    public static void selectCities(int index, int depth) {
        if (depth == k) {
            initialize();  // 방문 배열 초기화
            for (int city : selectedCities) {
                int x = city / n;
                int y = city % n;
                if (!visited[x][y]) {
                    bfs(x, y);
                }
            }
            maxCities = Math.max(maxCities, getVisitedCount());  // 최대 방문 도시 수 갱신
            return;
        }

        if (index >= n * n) return;

        // 도시 선택
        selectedCities.add(index);
        selectCities(index + 1, depth + 1);

        // 선택 취소 후 다음 도시 선택
        selectedCities.remove(selectedCities.size() - 1);
        selectCities(index + 1, depth);
    }

    // BFS 탐색
    private static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curX = current[0];
            int curY = current[1];
            int originHeight = grid[curX][curY];

            for (int dir = 0; dir < 4; dir++) {
                int nx = curX + dx[dir];
                int ny = curY + dy[dir];

                // 범위 내에 있으며, 방문하지 않았고, 높이 차이 조건을 만족하면 방문
                if (inRange(nx, ny) && !visited[nx][ny]) {
                    int heightDifference = Math.abs(grid[nx][ny] - originHeight);
                    if (heightDifference >= u && heightDifference <= d) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    // 방문 배열 초기화
    private static void initialize() {
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], false);
        }
    }

    // 격자 범위 내 여부 확인
    private static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    // 방문한 도시 수 계산
    private static int getVisitedCount() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) count++;
            }
        }
        return count;
    }
}