import java.io.*;
import java.util.*;

public class Main {
    static int n, m, q;
    static int[][] grid;
    static List<String[]> wind;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 처리
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        wind = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            wind.add(br.readLine().split(" "));
        }

        // 바람 처리
        for (int i = 0; i < q; i++) {
            int row = Integer.parseInt(wind.get(i)[0]) - 1;
            String direction = wind.get(i)[1];

            shift(row, direction); // 바람에 의해 행 시프트

            // 위로 전파
            propagateUp(row, direction);

            // 아래로 전파
            propagateDown(row, direction);
        }

        // 출력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 위쪽으로 전파
    public static void propagateUp(int row, String direction) {
        String newDirection = direction.equals("L") ? "R" : "L"; // 반대 방향으로 전파
        int current = row;

        while (current > 0) {
            if (isSame(current, current - 1)) { // 위쪽 행과 비교
                shift(current - 1, newDirection); // 반대 방향으로 시프트
                current--; // 한 행 위로 이동
                newDirection = newDirection.equals("L") ? "R" : "L"; // 다시 방향 바꿈
            } else {
                break; // 전파가 불가능하면 중단
            }
        }
    }

    // 아래쪽으로 전파
    public static void propagateDown(int row, String direction) {
        String newDirection = direction.equals("L") ? "R" : "L"; // 반대 방향으로 전파
        int current = row;

        while (current < n - 1) {
            if (isSame(current, current + 1)) { // 아래쪽 행과 비교
                shift(current + 1, newDirection); // 반대 방향으로 시프트
                current++; // 한 행 아래로 이동
                newDirection = newDirection.equals("L") ? "R" : "L"; // 다시 방향 바꿈
            } else {
                break; // 전파가 불가능하면 중단
            }
        }
    }

    // 같은 열에 같은 숫자가 존재하는지 확인
    public static boolean isSame(int r1, int r2) {
        for (int i = 0; i < m; i++) {
            if (grid[r1][i] == grid[r2][i]) return true;
        }
        return false;
    }

    // 특정 행을 왼쪽 또는 오른쪽으로 한 칸씩 시프트
    public static void shift(int row, String direction) {
        if (direction.equals("L")) { // 왼쪽으로 시프트
            int temp = grid[row][0];
            for (int i = 0; i < m - 1; i++) {
                grid[row][i] = grid[row][i + 1];
            }
            grid[row][m - 1] = temp;
        } else { // 오른쪽으로 시프트
            int temp = grid[row][m - 1];
            for (int i = m - 1; i > 0; i--) {
                grid[row][i] = grid[row][i - 1];
            }
            grid[row][0] = temp;
        }
    }
}