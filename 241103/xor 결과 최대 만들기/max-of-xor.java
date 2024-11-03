import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] number;
    static int maxXor = 0; // 최대 XOR 값을 저장할 변수
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        number = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, 0);
        System.out.println(maxXor); // 최대 XOR 값 출력
    }

    public static void solve(int depth, int cur) {
        if (depth == m) {
            int xorResult = 0;
            for (int num : result) {
                xorResult ^= num;
            }
            maxXor = Math.max(maxXor, xorResult); // 최대 XOR 값 갱신
            return;
        }

        for (int i = cur; i < number.length; i++) {
            result.add(number[i]);
            solve(depth + 1, i + 1);
            result.remove(result.size() - 1);
        }
    }
}