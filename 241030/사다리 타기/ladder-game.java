import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Pair implements Comparable<Pair> { 
    int x, y;
    public Pair(int x, int y) { 
        this.x = x; 
        this.y = y; 
    }
    @Override
    public int compareTo(Pair b) {
        if(x != b.x) return x - b.x;
        return y - b.y;
    }
}

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final int MAX_N = 11;
    
    public static int n, m;
    
    public static ArrayList<Pair> lines = new ArrayList<>();
    public static ArrayList<Pair> selectedLines = new ArrayList<>();
    
    public static int ans = INT_MAX;
    
    // 처음 상황과, 선택한 가로줄만 사용했을 때의
    // 상황을 시뮬레이션하여
    // 둘의 결과가 같은지 확인합니다.
    public static boolean possible() {
        // Step1. 시작 숫자를 셋팅합니다.
        int[] num1 = new int[MAX_N];
        int[] num2 = new int[MAX_N];
        for(int i = 0; i < n; i++)
            num1[i] = num2[i] = i;
        
        // Step2. 위에서부터 순서대로 적혀있는 
        // 가로줄에 대해 양쪽 번호에 해당하는 숫자를 바꿔줍니다. 
        for(int i = 0; i < (int) lines.size(); i++) {
            int idx = lines.get(i).y;
            int tmp = num1[idx];
            num1[idx] = num1[idx + 1];
            num1[idx + 1] = tmp;
        }
        for(int i = 0; i < (int) selectedLines.size(); i++) {
            int idx = selectedLines.get(i).y;
            int tmp = num2[idx];
            num2[idx] = num2[idx + 1];
            num2[idx + 1] = tmp;
        }
        
        // Step3. 두 상황의 결과가 동일한지 확인합니다.
        for(int i = 0; i < n; i++)
            if(num1[i] != num2[i])
                return false;
    
        return true;
    }
    
    public static void findMinLines(int cnt) {
        if(cnt == m) {
            if(possible())
                ans = Math.min(ans, (int) selectedLines.size());
            return;
        }
        
        selectedLines.add(lines.get(cnt));
        findMinLines(cnt + 1);
        selectedLines.remove(selectedLines.size() - 1);
        
        findMinLines(cnt + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        
        for(int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            lines.add(new Pair(b, a - 1));
        }
        
        Collections.sort(lines);

        findMinLines(0);
        
        System.out.print(ans);
    }
}