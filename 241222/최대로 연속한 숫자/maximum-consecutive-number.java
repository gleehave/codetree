import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int n, m;
    static TreeSet<Integer> remainingNumbers = new TreeSet<>(); // 남은 숫자들을 저장하는 TreeSet

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 처리
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 초기 수열 생성 (0부터 n까지)
        for (int i = 0; i <= n; i++) {
            remainingNumbers.add(i);
        }

        // 숫자 제거 및 결과 계산
        st = new StringTokenizer(br.readLine());
        StringBuilder result = new StringBuilder(); // 출력 최적화
        for (int i = 0; i < m; i++) {
            int numberToRemove = Integer.parseInt(st.nextToken());
            remainingNumbers.remove(numberToRemove); // 숫자 제거
            result.append(getLongestConsecutiveLength()).append("\n");
        }

        // 결과 출력
        System.out.print(result);
    }

    // 현재 남아 있는 숫자들로 최장 연속 부분 수열 길이를 계산하는 메서드
    public static int getLongestConsecutiveLength() {
        int maxLength = 0; // 최장 연속 수열 길이
        int currentLength = 0; // 현재 연속된 수열 길이
        Integer previous = null; // 이전 숫자를 추적

        for (int number : remainingNumbers) {
            if (previous == null || number == previous + 1) {
                currentLength++; // 연속된 숫자이면 길이 증가
            } else {
                maxLength = Math.max(maxLength, currentLength); // 최대 길이 갱신
                currentLength = 1; // 새로운 수열 시작
            }
            previous = number;
        }
        // 마지막 연속 수열 길이를 확인하여 갱신
        maxLength = Math.max(maxLength, currentLength);
        return maxLength;
    }
}
