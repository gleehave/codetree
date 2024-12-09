import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 처리
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        // 결과 값
        int ans = 0;
        for (int i = 0; i < n; i++) {
            long now = arr[i];
            Map<Long, Integer> count = new HashMap<>();

            for (int j = i + 1; j < n; j++) {
                long diff = k - now - arr[j];

                // diff가 존재하면 카운트 증가
                if (count.containsKey(diff)) {
                    ans += count.get(diff);
                }

                // arr[j]를 count에 추가
                count.put(arr[j], count.getOrDefault(arr[j], 0) + 1);
            }
        }

        // 결과 출력
        System.out.println(ans);
    }
}
