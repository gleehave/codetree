import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Cell implements Comparable<Cell> {
    int num, x, y;

    public Cell(int num, int x, int y) {
        this.num = num;
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Cell c) {
        return this.num - c.num;      // num 기준으로 오름차순 정렬합니다.
    }
}

public class Main {
    public static final int DIR_NUM = 4;
    public static final int MAX_N = 500;
    
    // 변수 선언
    public static int n;
    public static int[][] grid = new int[MAX_N][MAX_N];
    public static int[][] dp = new int[MAX_N][MAX_N];
    
    public static ArrayList<Cell> cells = new ArrayList<>();
    public static int ans = 0;
    
    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력
        n = sc.nextInt();

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();

        // 각 칸에 적혀있는 정수값 기준으로
        // 오름차순이 되도록 칸의 위치들을 정렬합니다.
        // 편하게 정렬하기 위해
        // (칸에 적혀있는 값, 행 위치, 열 위치) 순으로 넣어줍니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                cells.add(new Cell(grid[i][j], i, j));
        
        // 오름차순으로 정렬을 진행합니다.
        Collections.sort(cells);

        // 처음 DP 값들은 전부 1로 초기화해줍니다. (해당 칸에서 시작하는 경우)
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                dp[i][j] = 1;

        // 정수값이 작은 칸부터 순서대로 보며
        // 4방향에 대해 dp 값을 갱신해줍니다.
        for(int i = 0; i < cells.size(); i++) {
            int x = cells.get(i).x;
            int y = cells.get(i).y;
            int[] dx = new int[]{-1, 1,  0, 0};
            int[] dy = new int[]{ 0, 0, -1, 1};

            // 인접한 4개의 칸에 대해 갱신을 진행합니다.
            for(int j = 0; j < DIR_NUM; j++) {
                int nx = x + dx[j], ny = y + dy[j];
                if(inRange(nx, ny) && grid[nx][ny] > grid[x][y])
                    dp[nx][ny] = Math.max(dp[nx][ny], dp[x][y] + 1);
            }
        }

        // 전체 수들 중 최댓값을 찾습니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                ans = Math.max(ans, dp[i][j]);

        System.out.print(ans);
    }
}