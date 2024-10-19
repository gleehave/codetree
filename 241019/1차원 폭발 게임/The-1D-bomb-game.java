import java.util.Scanner;

public class Main {
    public static final int MAX_NUM = 100;
    
    public static int n, m, endOfArray, endOfTemp;
    public static int[] numbers = new int[MAX_NUM];
    public static int[] temp = new int[MAX_NUM];
    
    // 주어진 시작점에 대하여 
    // 부분 수열의 끝 위치를 반환합니다.
    public static int getEndIdxOfExplosion(int startIdx, int currNum) {
        int endIdx = startIdx + 1;
        while(endIdx < endOfArray) {
            if(numbers[endIdx] == currNum)
                endIdx++;
            else{
                break;
            }
        }
    
        return endIdx - 1;
    }
    
    // 터져야 할 폭탄들에 대해 터졌다는 의미로 0을 채워줍니다.
    public static void fillZero(int startIdx, int endIdx) {
        for(int i = startIdx; i <= endIdx; i++) {
            numbers[i] = 0;
        }
    }
    
    // Arr에서 폭탄이 터진 이후의 결과를 Temp에 임시로 저장합니다. 
    public static void moveToTemp() {
        endOfTemp = 0;
        for(int i = 0; i < endOfArray; i++) {
            if(numbers[i] != 0) {
                temp[endOfTemp++] = numbers[i];
            }
        }
    }
    
    // Temp배열을 그대로 Copy하여 Arr에 저장합니다.
    public static void copy() {
        endOfArray = endOfTemp;
        for(int i = 0; i < endOfArray; i++) {
            numbers[i] = temp[i];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();
        for(int i = 0; i < n; i++)
            numbers[i] = sc.nextInt();
        endOfArray = n;

        boolean didExplode;
        do {
            didExplode = false;
            for(int currIdx = 0; currIdx < endOfArray; currIdx++) {  
                // 각 위치마다 그 뒤로 폭탄이 m개 이상 있는지 확인합니다.
                
                // 이미 터지기로 예정되어있는 폭탄은 패스합니다.
                if(numbers[currIdx] == 0) { 
                    continue;
                }
                // currIdx로부터 연속하여 같은 숫자를 갖는 폭탄 중 
                // 가장 마지막 위치를 찾아 반환합니다.
                int endIdx = getEndIdxOfExplosion(currIdx, numbers[currIdx]);

                if(endIdx - currIdx + 1 >=  m) {
                    // 연속한 숫자의 개수가 m개 이상인 경우 폭탄이 터졌음을 기록해줍니다.
                    fillZero(currIdx, endIdx);
                    didExplode = true;
                }
            }
            
            // Arr에서 폭탄이 터진 이후의 결과를 Temp에 임시로 저장합니다. 
            moveToTemp();
            // Temp배열을 그대로 Copy하여 Arr에 저장합니다.
            copy();       
        }
        while(didExplode); // 더 이상 폭탄이 터질 수 없을 때까지 반복합니다.

        System.out.println(endOfArray);
        for(int i = 0; i < endOfArray; i++)
            System.out.println(numbers[i]);
    }
}