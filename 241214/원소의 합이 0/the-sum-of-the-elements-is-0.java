import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static final int MAX_N = 5000;

    public static int n;
    public static int[] A = new int[MAX_N];
    public static int[] B = new int[MAX_N];
    public static int[] C = new int[MAX_N];
    public static int[] D = new int[MAX_N];
    public static HashMap<Integer, Integer> freq = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 입력:
        n = sc.nextInt();
        for(int i = 0; i < n; i++) A[i] = sc.nextInt();
        for(int i = 0; i < n; i++) B[i] = sc.nextInt();
        for(int i = 0; i < n; i++) C[i] = sc.nextInt();
        for(int i = 0; i < n; i++) D[i] = sc.nextInt();

        long ans = 0;

        // A 수열에서 숫자 하나, B 수열에서 숫자 하나를 골랐을 때
        // 나올 수 있는 두 숫자의 합들을 hashmap에 기록해줍니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) {
                int key = A[i] + B[j];
                freq.put(key, freq.getOrDefault(key, 0) + 1);
            }

        // C, D 수열을 순회하며 쌍을 만들어줍니다.
        // 앞서 계산한 hashmap을 이용하면
        // C, D 수열에서 고른 값으로 A, B와 쌍을 만들 때
        // 총합이 0이 되는 쌍의 개수를 쉽게 구할 수 있습니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) {
                int diff = - C[i] - D[j];
                if(freq.getOrDefault(diff, 0) > 0)
                    ans += freq.get(diff);
            }

        // 출력:
        System.out.println(ans);
    }
}