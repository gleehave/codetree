import java.util.Scanner;

public class Main {
    public static final int MAX_NUM = 19;
    
    public static int[] dp = new int[MAX_NUM + 1];
    
    public static int numOfUniqueBST(int n) {
        int numberOfUniqueBst = 0;
    
        for(int i = 0; i < n; i++)
            numberOfUniqueBst += dp[i] * dp[n - i - 1];
    
        return numberOfUniqueBst;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++)
            dp[i] = numOfUniqueBST(i);

        System.out.print(dp[n]);
    }
}