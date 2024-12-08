import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n, k;
    public static long[] arr = new long[MAX_N];
    public static HashMap<Long, Integer> count = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        k = sc.nextInt();
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        int ans = 0;

        // 배열을 앞에서부터 순회하며 쌍을 만들어줍니다.
        for(int i = 0; i < n; i++) {
            long diff = k - arr[i];
            // 가능한 모든 쌍의 수를 세어줍니다.
            if(count.containsKey(diff))
                ans += count.get(diff);

            // 현재 숫자의 개수를 하나 증가시켜줍니다.
            if(!count.containsKey(arr[i]))
                count.put(arr[i], 1);
            else
                count.put(arr[i], count.get(arr[i]) + 1);
        }

        System.out.print(ans);
    }
}