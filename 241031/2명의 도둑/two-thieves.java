import java.util.Scanner;

public class Main {
    public static final int MAX_M = 5;
    public static final int MAX_N = 10;
    
    // 전역 변수 선언:
    public static int n, m, c;
    public static int[][] weight = new int[MAX_N][MAX_N];
    
    public static int[] a = new int[MAX_M];
    
    public static int ans;
    public static int maxVal;
    
    public static void findMaxSum(int currIdx, int currWeight, int currVal) {
        if(currIdx == m) {
            // 고른 무게들의 합이 c를 넘지 않는 경우에만 갱신합니다.
            if(currWeight <= c)
                maxVal = Math.max(maxVal, currVal);
            return;
        }
    
        // currIdx index 에 있는 숫자를 선택하지 않은 경우
        findMaxSum(currIdx + 1, currWeight, currVal);
        
        // currIdx index 에 있는 숫자를 선택한 경우
        // 무게는 a[currIdx] 만큼 늘지만
        // 문제 정의에 의해 가치는 a[currIdx] * a[currIdx] 만큼 늘어납니다.
        findMaxSum(currIdx + 1, currWeight + a[currIdx], 
                   currVal + a[currIdx] * a[currIdx]);
    }
    
    // (sx, sy) ~ (sx, sy + m - 1) 까지의 숫자들 중 적절하게 골라
    // 무게의 합이 c를 넘지 않게 하면서 얻을 수 있는 최대 가치를 반환합니다.
    public static int findMax(int sx, int sy) {
        // 문제를 a[0] ~ a[m - 1]까지 m개의 숫자가 주어졌을 때
        // 적절하게 골라 무게의 합이 c를 넘지 않게 하면서 얻을 수 있는 최대 가치를 
        // 구하는 문제로 바꾸기 위해
        // a 배열을 적절하게 채워넣습니다. 
        for(int i = sy; i <= sy + m - 1; i++)
            a[i - sy] = weight[sx][i];
        
        // 2^m개의 조합에 대해 최적의 값을 구합니다.
        maxVal = 0;
        findMaxSum(0, 0, 0);
        return maxVal;
    }
    
    // [a, b], [c, d] 이 두 선분이 겹치는지 판단합니다.
    public static boolean intersect(int a, int b, int c, int d) {
        // 겹치지 않을 경우를 계산하여 그 결과를 반전시켜 반환합니다.
        return ! (b < c || d < a); 
    }
    
    // 두 도둑의 위치가 올바른지 판단합니다.
    public static boolean possible(int sx1, int sy1, int sx2, int sy2) {
        // 두 도둑이 훔치려는 물건의 범위가 
        // 격자를 벗어나는 경우에는 불가능합니다.
        if(sy1 + m - 1 >= n || sy2 + m - 1 >= n) 
            return false;                        
        
        // 두 도둑이 훔칠 위치의 행이 다르다면
        // 겹칠 수가 없으므로 무조건 가능합니다.
        if(sx1 != sx2)                          
            return true;                        
        
        // 두 구간끼리 겹친다면
        // 불가능합니다.
        if(intersect(sy1, sy1 + m - 1, sy2, sy2 + m - 1)) 
            return false;                               
        
        // 행이 같으면서 구간끼리 겹치지 않으면
        // 가능합니다.
        return true;                             
    }                                           

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();
        c = sc.nextInt();
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) 
                weight[i][j] = sc.nextInt();

        // 첫 번째 도둑은 (sx1, sy1) ~ (sx1, sy1 + m - 1) 까지 물건을 훔치려 하고
        // 두 번째 도둑은 (sx2, sy2) ~ (sx2, sy2 + m - 1) 까지의 물건을 
        // 훔치려 한다고 했을 때 가능한 모든 위치를 탐색해봅니다.
        for(int sx1 = 0; sx1 < n; sx1++)
            for(int sy1 = 0; sy1 < n; sy1++)
                for(int sx2 = 0; sx2 < n; sx2++)
                    for(int sy2 = 0; sy2 < n; sy2++) {
                        // 두 도둑의 위치가 올바른지 판단합니다.
                        // 각 위치에서의 얻을 수 있는 최대 가치의 합을 ans와 
                        // 비교하여 갱신합니다.
                        if(possible(sx1, sy1, sx2, sy2))
                            ans = Math.max(ans, findMax(sx1, sy1) 
                                         + findMax(sx2, sy2));
                    }
        
        // 출력:
        System.out.print(ans);
    }
}