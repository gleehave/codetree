import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Tuple implements Comparable<Tuple> {
    int x, y, z;
    public Tuple(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public int compareTo(Tuple b) {
        if(x != b.x) return x - b.x;
        if(y != b.y) return y - b.y;
        return z - b.z;
    }
}

public class Main {
    public static final int DIR_NUM = 4;
    public static final int ASCII_NUM = 128;
    public static final int MAX_N = 50;
    
    public static int n, m, t, k;
    public static ArrayList<Tuple>[][] grid = new ArrayList[MAX_N][MAX_N];
    public static ArrayList<Tuple>[][] nextGrid = new ArrayList[MAX_N][MAX_N];
    
    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
    
    public static Tuple NextPos(int x, int y, int vnum, int moveDir) {
        int[] dx = new int[]{-1, 0, 0, 1};
        int[] dy = new int[]{0, 1, -1, 0};
        
        // vnum 횟수만큼 이동한 이후의 위치를 반환합니다.
        while(vnum-- > 0) {
            int nx = x + dx[moveDir], ny = y + dy[moveDir];
            // 벽에 부딪히면
            // 방향을 바꾼 뒤 이동합니다.
            if(!inRange(nx, ny)) {
                moveDir = 3 - moveDir;
                nx = x + dx[moveDir]; ny = y + dy[moveDir];
            }
            x = nx; y = ny;
        }
        return new Tuple(x, y, moveDir);
    }
    
    public static void moveAll() {
        for(int x = 0; x < n; x++)
            for(int y = 0; y < n; y++)
                for(int i = 0; i < (int) grid[x][y].size(); i++) {
                    int v = grid[x][y].get(i).x;
                    int num = grid[x][y].get(i).y;
                    int moveDir = grid[x][y].get(i).z;
                    
                    int nextX, nextY, nextDir;
                    // v값이 음수이므로, -를 붙여 넘겨줍니다.
                    nextX = NextPos(x, y, -v, moveDir).x;
                    nextY = NextPos(x, y, -v, moveDir).y;
                    nextDir = NextPos(x, y, -v, moveDir).z;

                    nextGrid[nextX][nextY].add(
                        new Tuple(v, num, nextDir)
                    );
                }
    }
    
    public static void selectMarbles() {
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) 
                if((int) nextGrid[i][j].size() >= k) {
                    // 우선순위가 높은 k개만 남겨줍니다.
                    Collections.sort(nextGrid[i][j]);
                    while((int) nextGrid[i][j].size() > k)
                        nextGrid[i][j].remove(nextGrid[i][j].size() - 1);
                }
    }
    
    public static void simulate() {
        // Step1. nextGrid를 초기화합니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                nextGrid[i][j] = new ArrayList<>();
            
        // Step2. 구슬들을 전부 움직입니다.
        moveAll();
        
        // Step3. 각 칸마다 구슬이 최대 k개만 있도록 조정합니다.
        selectMarbles();
        
        // Step4. nextGrid 값을 grid로 옮겨줍니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = (ArrayList<Tuple>) nextGrid[i][j].clone();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        t = sc.nextInt();
        k = sc.nextInt();

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = new ArrayList<>();
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                nextGrid[i][j] = new ArrayList<>();
        
        int[] dirMapper = new int[ASCII_NUM];
        dirMapper['U'] = 0;
        dirMapper['R'] = 1;
        dirMapper['L'] = 2;
        dirMapper['D'] = 3;
        
        for(int i = 0; i < m; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            char d = sc.next().charAt(0);
            int v = sc.nextInt();
            
            // 살아남는 구슬의 우선순위가 더 빠른 속도, 더 큰 번호 이므로
            // 정렬시 속도가 먼저 내림차순, 그 다음에는 번호가 내림차순으로 오도록
            // (-속도, -번호, 방향) 순서를 유지합니다.
            grid[r - 1][c - 1].add(
                new Tuple(-v, -(i + 1), dirMapper[d])
            );
        }
        
        // t초에 걸쳐 시뮬레이션을 반복합니다.
        while(t-- > 0)
            simulate();
        
        int ans = 0;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                ans += (int) grid[i][j].size();
        
        System.out.print(ans);
    }
}