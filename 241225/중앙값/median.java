import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
        while (t-- > 0) {
            int m = Integer.parseInt(br.readLine()); // 수열의 크기
            StringTokenizer st;
            StringBuilder result = new StringBuilder();
            // 힙 초기화
            PriorityQueue<Integer> leftHeap = new PriorityQueue<>(Collections.reverseOrder()); // 최대 힙
            PriorityQueue<Integer> rightHeap = new PriorityQueue<>(); // 최소 힙
            
            for (int i = 1; i <= m; i++) {
                if (i % 10 == 1) { // 새로운 줄 입력
                    st = new StringTokenizer(br.readLine());
                }

                int num = Integer.parseInt(st.nextToken());

                // 최대 힙과 최소 힙 관리
                if (leftHeap.isEmpty() || num <= leftHeap.peek()) {
                    leftHeap.offer(num);
                } else {
                    rightHeap.offer(num);
                }

                // 힙 균형 조정
                if (leftHeap.size() > rightHeap.size() + 1) {
                    rightHeap.offer(leftHeap.poll());
                } else if (rightHeap.size() > leftHeap.size()) {
                    leftHeap.offer(rightHeap.poll());
                }

                // 홀수 번째 원소의 중앙값 출력
                if (i % 2 == 1) {
                    result.append(leftHeap.peek()).append(" ");
                }
            }
            System.out.println(result);
        }
    }
}