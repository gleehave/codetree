import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] number;
    static boolean[] used;
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        used = new boolean[n];
        number = new int[n];
        for (int i = 0; i < n; i++) {
            number[i] = i + 1;
        }

        // 사전순 역순 출력을 위해 마지막 수열부터 생성
        solve(0);
    }

    public static void solve(int depth) {
        if (depth == n) {
            for (int i = 0; i < result.size(); i++) {
                System.out.print(result.get(i) + " ");
            }
            System.out.println();
            return;
        }

        // 역순으로 탐색하여 사전순으로 가장 뒤에 있는 수열부터 생성
        for (int i = n - 1; i >= 0; i--) {
            if (used[i]) continue;

            used[i] = true;
            result.add(number[i]);

            solve(depth + 1);

            // 백트래킹: 현재 선택을 되돌림
            used[i] = false;
            result.remove(result.size() - 1);
        }
    }
}