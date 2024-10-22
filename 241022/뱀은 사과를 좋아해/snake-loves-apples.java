/*
머리를 먼저 1칸 움직이고, 가장 끝이 사라진다.
사과가 있으면 몸 길이 + 1

뱀이 전부 움직였거나 격자를 벗어났거나 몸에 부딪혔다면 종료.

*/

import java.io.*;
import java.util.*;

class Snake{
    int r, c;
    String direction;
    List<int[]> body = new ArrayList<>();

    public Snake(String direction){
        this.direction = direction;
    }

}

public class Main {

    static int n, m, k;
    static int[][] grid;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1}; // 상하좌우

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        if (m > 0){
            for(int i=0; i<m; i++){ // 사과 표시
                st = new StringTokenizer(br.readLine());                
                grid[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
            }
        }

        Snake snake = new Snake("L"); // 여기 방향은 임의로 생성
        snake.r = 0;
        snake.c = 0;

        int time = 0;
        while(k-- > 0){
            st = new StringTokenizer(br.readLine());
            snake.direction = st.nextToken();
            int movement = Integer.parseInt(st.nextToken());
            int d = decideDirection(snake.direction);

            for(int move=0; move<movement; move++){ // 움직인다.
                int nextR = snake.r + dr[d];
                int nextC = snake.c + dc[d];

                if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= n){
                    System.out.println(time+1);
                    return;
                }

                if (snake.body.contains(new int[]{nextR, nextC})){
                    System.out.println(time+1);
                    return;
                }

                if (grid[nextR][nextC] == 1){
                    snake.body.add(new int[]{nextR, nextC});
                    snake.r = nextR;
                    snake.c = nextC;
                } else {
                    if (snake.body.size() > 0){
                        snake.body.remove(snake.body.size()-1);
                        snake.body.add(new int[] {snake.r, snake.c});
                    } 
                    snake.r = nextR;
                    snake.c = nextC;
                }
                time++;
            }
        }
        System.out.println(time);

    } // main

    public static int decideDirection(String dir){
        int direction = 0;

        switch(dir){ // 상하좌우
            case "L":
                direction = 2;
                break;
            case "R":
                direction = 3;                
                break;
            case "U":
                direction = 0;
                break;
            case "D":
                direction = 1;
                break;
        }
        return direction;
    }
}