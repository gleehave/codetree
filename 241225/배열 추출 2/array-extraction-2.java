import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine()); // 연산의 개수

        // PriorityQueue 초기화 (절댓값 우선, 값이 같은 경우 원래 값 우선)
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int absA = Math.abs(a);
            int absB = Math.abs(b);
            if (absA == absB) {
                return Integer.compare(a, b); // 절댓값이 같으면 원래 값 기준으로 정렬
            }
            return Integer.compare(absA, absB); // 절댓값 기준으로 정렬
        });

        while (n-- > 0) {
            int x = Integer.parseInt(br.readLine());

            if (x != 0) {
                pq.offer(x); // 값 추가
            } else {
                if (pq.isEmpty()) {
                    sb.append(0).append("\n"); // 비어 있으면 0 출력
                } else {
                    sb.append(pq.poll()).append("\n"); // 절댓값 가장 작은 값 출력 후 제거
                }
            }
        }

        System.out.print(sb);
    }
}