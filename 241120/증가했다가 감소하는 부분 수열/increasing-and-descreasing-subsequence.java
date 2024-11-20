import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] sequence = new int[N];
        for (int i = 0; i < N; i++) {
            sequence[i] = scanner.nextInt();
        }
        
        int[] increase = new int[N];
        int[] decrease = new int[N];
        
        for (int i = 0; i < N; i++) {
            increase[i] = 1;
            for (int j = 0; j < i; j++) {
                if (sequence[j] < sequence[i]) {
                    increase[i] = Math.max(increase[i], increase[j] + 1);
                }
            }
        }
        
        for (int i = N - 1; i >= 0; i--) {
            decrease[i] = 1;
            for (int j = N - 1; j > i; j--) {
                if (sequence[j] < sequence[i]) {
                    decrease[i] = Math.max(decrease[i], decrease[j] + 1);
                }
            }
        }
        
        int maxLength = 0;
        for (int i = 0; i < N; i++) {
            maxLength = Math.max(maxLength, increase[i] + decrease[i] - 1);
        }
        
        System.out.println(maxLength);
    }
}
