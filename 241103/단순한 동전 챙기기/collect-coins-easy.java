/*
최소 3개의 동전을 수집해야한다.
동전은 항상 숫자가 증가하는 순서이다.

동전 숫자 최대 9개 중 3개를 선택한다. (중복없이)
- 3개를 선택하지 못하면 -1 이다.
- 이동은..? DFS로?

*/

import java.io.*;
import java.util.*;

public class Main {
    static int n, result;
    static int startR, startC, endR, endC;
    static String[][] grid;

    static boolean[] usedCoin;
    static List<Integer> coin = new ArrayList<>();
    static List<Integer> route = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        grid = new String[n][n];
        for(int i=0; i<n; i++){
            String[] row = br.readLine().split("");
            for(int j=0; j<n; j++){
                grid[i][j] = row[j];
                if (grid[i][j].equals("S")){
                    startR = i;
                    startC = j;
                } else if (grid[i][j].equals("E")){
                    endR = i;
                    endC = j;
                } else if (!grid[i][j].equals(".")){
                    coin.add(Integer.parseInt(grid[i][j]));
                }
            }
        } // grid 입력

        if (coin.size() < 3){
            System.out.println(-1);
            return;
        }
        usedCoin = new boolean[coin.size()];
        Collections.sort(coin);

        result = Integer.MAX_VALUE;
        solve(0, 0);
        System.out.println(result);
    }

    public static void solve(int depth, int cur){
        if (depth == 3){
            move(startR, startC, endR, endC);
            return;
        }

        for(int i=cur; i<coin.size(); i++){
            if (usedCoin[i]) continue;
            
            usedCoin[i] = true;
            route.add(coin.get(i));

            if ((route.size() >= 2) && !(route.get(route.size()-1) > route.get(route.size()-2))){
                usedCoin[i] = false;
                route.remove(route.size()-1);
                continue;
            }

            solve(depth+1, cur+1);

            usedCoin[i] = false;
            route.remove(route.size()-1);
        }
    } // solve

    public static void move(int startR, int startC, int endR, int endC){
        int count = 0;
        int tempR = startR;
        int tempC = startC;
        int midleR = -1;
        int midleC = -1;

        for(int goal: route){
            for(int r=0; r<n; r++){
                for(int c=0; c<n; c++){
                    if (grid[r][c].equals(".") || grid[r][c].equals("S") || grid[r][c].equals("E")) continue;
                    if (Integer.parseInt(grid[r][c]) == goal){
                        midleR = r;
                        midleC = c;
                    }
                }
            }

            count += Math.abs(tempR - midleR) + Math.abs(tempC - midleC);
            tempR = midleR;
            tempC = midleC;
        }

        count += Math.abs(tempR - endR) + Math.abs(tempC - endC);
        result = Math.min(result, count);
    }
}