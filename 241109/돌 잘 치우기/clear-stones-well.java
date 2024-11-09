import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class Pair { 
    int x, y;
    public Pair(int x, int y) { 
        this.x = x; 
        this.y = y; 
    } 
}

public class Main {
    public static final int DIR_NUM = 4;
    public static final int MAX_N = 100;
    
    // 전역 변수 선언:
    public static int n, k, m;
    public static int[][] a = new int[MAX_N][MAX_N];
    
    public static int ans;

    public static ArrayList<Pair> sPos = new ArrayList<>();
    public static ArrayList<Pair> stonePos = new ArrayList<>();
    public static ArrayList<Pair> selectedStones = new ArrayList<>();
    
    // bfs에 필요한 변수들 입니다.
    public static Queue<Pair> q = new LinkedList<>();
    public static boolean[][] visited = new boolean[MAX_N][MAX_N];
    
    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
    
    public static boolean canGo(int x, int y) {
        return inRange(x, y) && a[x][y] == 0 && !visited[x][y];
    }
    
    public static void BFS() {
        // queue에 남은 것이 없을때까지 반복합니다.
        while(!q.isEmpty()) {
            // queue에서 가장 먼저 들어온 원소를 뺍니다.
            Pair currPos = q.poll();
            int x = currPos.x, y = currPos.y;
    
            int[] dx = new int[]{1, -1, 0, 0};
            int[] dy = new int[]{0, 0, 1, -1};
    
            // queue에서 뺀 원소의 위치를 기준으로 4방향을 확인해봅니다.
            for(int dir = 0; dir < DIR_NUM; dir++) {
                int nx = x + dx[dir], ny = y + dy[dir];
    
                // 아직 방문한 적이 없으면서 갈 수 있는 곳이라면
                // 새로 queue에 넣어주고 방문 여부를 표시해줍니다. 
                if(canGo(nx, ny)){
                    q.add(new Pair(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
    }
    
    public static int calc() {
        for(int i = 0; i < m; i++) {
            int x = selectedStones.get(i).x, y = selectedStones.get(i).y;
            a[x][y] = 0;
        }
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                visited[i][j] = false;
            
        // k개의 시작점을 queue에 넣고 시작합니다.
        // BFS는 여러 시작점에서 시작하여
        // 이동 가능한 칸을 전부 탐색하는 것이 가능합니다.
        for(int i = 0; i < k; i++) {
            q.add(sPos.get(i));
            visited[sPos.get(i).x][sPos.get(i).y] = true;
        }
    
        BFS();
        
        for(int i = 0; i < m; i++) {
            int x = selectedStones.get(i).x, y = selectedStones.get(i).y;
            a[x][y] = 1;
        }
    
        int cnt = 0;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(visited[i][j])
                    cnt++;
        
        return cnt;
    }
    
    public static void findMax(int idx, int cnt) {
        if(idx == (int) stonePos.size()) {
            if(cnt == m)
                ans = Math.max(ans, calc());
            return;
        }
        
        selectedStones.add(stonePos.get(idx));
        findMax(idx + 1, cnt + 1);
        selectedStones.remove(selectedStones.size() - 1);
        
        findMax(idx + 1, cnt);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        k = sc.nextInt();
        m = sc.nextInt();

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
                if(a[i][j] == 1)
                    stonePos.add(new Pair(i, j));
            }
        
        for(int i = 0; i < k; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            r--; c--;
            sPos.add(new Pair(r, c));
        }
        
        findMax(0, 0);
        
        System.out.print(ans);
    }
}