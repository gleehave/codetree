import java.io.*;
import java.util.*;

class Ball{
    int index;
    int r, c, d, w;

    public Ball(int r, int c, int d, int w, int index){
        this.r = r;
        this.c = c;
        this.d = d;
        this.w = w;
        this.index = index;
    }
}

public class Main {
    static int n, m, t;
    static int[][] grid;
    static int[] dr = {-1, 0, 0, 1}; // 상 좌 우 하
    static int[] dc = {0, -1, 1, 0};
    static Ball[] list;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        list = new Ball[m];
        grid = new int[n][n];
        for(int i=1; i<=m; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = convertDir(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            grid[r][c] = 1; // 현재위치에서 구슬 개수
            list[i-1] = new Ball(r, c, d, w, i);
            
        }

        while(t-- > 0){

            // 1. Ball이 움직인다.
            move();

            // 2. 같은 위치면 합친다.
            // 무게 더하기, 번호는 큰 순서로 지정, 큰 순서의 방향으로 지정
            collision();
        }

        int maxWeight = 0;
        int maxCount = 0;
        for(int i=0; i<m; i++){
            if (list[i] != null){
                maxCount++;
                maxWeight = Math.max(maxWeight, list[i].w);
            }
        }
        System.out.println(maxCount + " " +maxWeight);

    }

    public static void collision(){
        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                if (grid[r][c] >= 2){
                    int newWeight = 0;
                    int maxIndex = 0;
                    int newDir = -1;

                    for(int i=0; i<m; i++){
                        if (list[i] == null) continue;

                        if (list[i].r == r && list[i].c == c){
                            newWeight += list[i].w;
                            maxIndex = Math.max(maxIndex, list[i].index);
                        }
                    }

                    for(int i=0; i<m; i++){
                        if (list[i] == null) continue;
                        if (list[i].index == maxIndex) newDir = list[i].d;
                    }

                    for(int i=0; i<m; i++){
                        if (list[i] == null) continue;
                        if (list[i].r == r && list[i].c == c) list[i] = null;
                    }

                    for(int i=0; i<m; i++){
                        if (list[i] == null){
                            list[i] = new Ball(r, c, newDir, newWeight, maxIndex);
                            break;
                        }
                    }
                    grid[r][c] = 1;
                }
            }
        }
    }

    public static void move(){
        for(int i=0; i<m; i++){
            if (list[i] == null) continue;

            int nextR = list[i].r + dr[list[i].d];
            int nextC = list[i].c + dc[list[i].d];
            int nextDir = list[i].d;

            if (nextR < 0 || nextC < 0 || nextR >=n || nextC >= n){
                nextDir = 3 - list[i].d;
                list[i].d = nextDir;
                continue;
            }

            grid[list[i].r][list[i].c]--;
            list[i].r = nextR;
            list[i].c = nextC;
            grid[list[i].r][list[i].c]++;   
        }
    }

    public static int convertDir(String dir){
        int d = -1;

        switch(dir){
            case "U":
                d = 0;
                break;
            case "D":
                d = 3;
                break;
            case "L":
                d = 1;
                break;
            case "R":
                d = 2;
                break;
        }
        return d;
    }
}