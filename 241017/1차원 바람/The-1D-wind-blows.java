/*

r을 기준으로 r--는 위쪽, r++는 아래쪽으로 전파된다고 생각하자.

1. L, R에 맞춰서 Shift
2. r-1, r+1에서 같은 열에서 값이 1개라도 같은지 조건 체크
    r-1: true -> r--; (d + 1) % 2;
    r-1: false -> 스탑

    r+1: true -> r++;
    r+1: false -> 스탑
*/


import java.io.*;
import java.util.*;

public class Main {
    static int n, m, q;
    static int[][] grid;
    static List<String[]> wind;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        wind = new ArrayList<>();
        for(int i=0; i<q; i++){
            wind.add(br.readLine().split(" "));
        }

        for(int i=0; i<wind.size(); i++){
            int row = Integer.parseInt(wind.get(i)[0]) - 1;
            String direction = wind.get(i)[1];

            shift(row, direction);

            if (isUp(row)){
                int current = row;
                String newDirection = direction;
                while(current >= 0){
                    current--;
                    if (newDirection.equals("L")){
                        shift(current, "R");
                        newDirection = "R";
                    } else {
                        shift(current, "L");
                        newDirection = "R";
                    }
                    if (!isUp(current)) break;
                }
            } else if (isDown(row)){
                int current = row;
                String newDirection = direction;
                while(current < n - 1){
                    current++;
                    if (newDirection.equals("L")){
                        shift(current, "R");
                        newDirection = "R";
                    } else {
                        shift(current, "L");
                        newDirection = "L";
                    }
                    if (!isDown(current)) break;
                }
            }


        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isUp(int row){
        for(int i=0; i<m; i++){
            if (grid[row][i] == grid[row-1][i]) return true;
        }
        return false;
    }

    public static boolean isDown(int row){
        for(int i=0; i<m; i++){
            if (grid[row][i] == grid[row+1][i]) return true;
        }
        return false;
    }

    public static void shift(int row, String direction){
        int temp = -1;
        if (direction.equals("L")){
            temp = grid[row][m-1];
            for(int i=m-1; i>0; i--){
                grid[row][i] = grid[row][i-1];
            }
            grid[row][0] = temp;
        } else {
            temp = grid[row][0];
            for(int i=0; i<m-1; i++){
                grid[row][i] = grid[row][i+1];
            }
            grid[row][m-1] = temp;
        }
    }
}