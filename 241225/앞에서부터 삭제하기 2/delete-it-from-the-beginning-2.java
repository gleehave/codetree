import java.util.Scanner;
import java.util.PriorityQueue;

public class Main {
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n;
    public static int[] arr = new int[MAX_N];
    public static int sumVal;
    public static double maxAvg;
    public static PriorityQueue<Integer> pq = new PriorityQueue<>();  

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        pq.add(arr[n - 1]);
        sumVal += arr[n - 1];
        // k가 N - 2일 때부터 1일 때까지 거꾸로 탐색합니다.
        // priority queue를 이용하여 진행합니다.
        for(int i = n - 2; i >= 1; i--) {
            // 앞에서부터 K개를 삭제하고 나면
            // 뒤에 i ~ n - 1 까지의 숫자만이 남습니다.
            pq.add(arr[i]);
            sumVal += arr[i];

            // 남아있는 정수 중 가장 작은 숫자를 찾아
            // 그 숫자를 제외한 평균을 구합니다.
            double avg = (double)(sumVal - pq.peek()) / (n - i - 1);

            // 평균이 최대가 된다면 정답을 현재 평균으로 갱신해줍니다.
            if(maxAvg < avg)
                maxAvg = avg;
        }

        // 평균값의 최대를 출력합니다.
        System.out.printf("%.2f", maxAvg);
    }
}