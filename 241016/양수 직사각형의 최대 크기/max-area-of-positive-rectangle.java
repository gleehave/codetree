/*
1. 시작점을 선택하자
2. 시작점을 기준으로 직사각형으로 표현하기 위한 다른 꼭지점을 찾는다.
3. 범위 안에서 음수 체크
    - 음수면 pass
    - 양수면 직사각형 크기 계산.

*/

import java.util.*;
import java.io.*;

public class Main {
    
    static int maxSum, maxCount;
    static int n, m;
    static int[][] grid;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        maxCount = 0;
        maxSum = -1;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                for(int k=0; k<n; k++){
                    for(int l=0; l<m; l++){
                        if (inRange(i, j, k, l)){
                            if (maxSum <= getGridSum(i, j, k, l)){
                                maxCount = Math.max(maxCount, countingGrid(i, j, k, l));
                            }
                        }
                    }
                }
            }
        }

        if (maxCount>0) System.out.println(maxCount);
        else System.out.println(-1);
    }

    public static boolean inRange(int si, int sj, int ek, int el){
        for(int r=si; r<=ek ;r++){
            for(int c=sj; c<=el; c++){
                if (grid[r][c] <= 0) return false;
            }
        }
        return true;
    }

    public static int getGridSum(int si, int sj, int ek, int el){
        int sum = 0;
        for(int r=si; r<=ek ;r++){
            for(int c=sj; c<=el; c++){
                sum += grid[r][c];
            }
        }
        return sum;
    }

    public static int countingGrid(int si, int sj, int ek, int el){
        int count = 0;
        for(int r=si; r<=ek ;r++){
            for(int c=sj; c<=el; c++){
                count++;
            }
        }
        return count;
    }
}