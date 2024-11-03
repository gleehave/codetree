import java.io.*;
import java.util.*;

public class Main {
    static int n, min;
    static int[] number;
    static boolean[] used;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        number = new int[2 * n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * n; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }

        min = Integer.MAX_VALUE;
        used = new boolean[2 * n];
        solve(0, 0);
        System.out.println(min);
    }

    public static void solve(int depth, int cur) {
        if (depth == n) { // 절반의 숫자를 선택하면 두 그룹을 나눔
            int group1 = 0, group2 = 0;

            for (int i = 0; i < 2 * n; i++) {
                if (used[i]) {
                    group1 += number[i];
                } else {
                    group2 += number[i];
                }
            }

            min = Math.min(min, Math.abs(group1 - group2)); // 그룹 차이의 최소값 갱신
            return;
        }

        for (int i = cur; i < 2 * n; i++) {
            if (used[i]) continue;
            used[i] = true;
            solve(depth + 1, i + 1); // 다음 호출에서는 i + 1부터 시작
            used[i] = false;
        }
    }
}