import java.util.Scanner;

public class Main {
    public static final int MAX_N = 100;
    public static final int DIR_NUM = 4;
    
    // 전역 변수 선언:
    public static int n;
    
    public static int currX, currY, currDir; // 현재 위치와 방향을 의미합니다.
    public static char[][] a = new char[MAX_N + 1][MAX_N + 1];
    
    // 미로 탈출이 불가능한지 여부를 판단하기 위해
    // 동일한 위치에 동일한 방향으로 진행했던 적이 있는지를
    // 표시해주는 배열입니다.
    public static boolean[][][] visited = new boolean[MAX_N + 1][MAX_N + 1][DIR_NUM];
    
    public static int elapsedTime;
    
    // 범위가 격자 안에 들어가는지 확인합니다.
    public static boolean inRange(int x, int y) {
        return 1 <= x && x <= n && 1 <= y && y <= n;
    }
    
    // 해당 위치에 벽이 있으면 이동이 불가합니다.
    public static boolean wallExist(int x, int y) {
        return inRange(x, y) && a[x][y] == '#';
    }
    
    // 조건에 맞춰 움직여봅니다.
    public static void simulate() {
        // 현재 위치에 같은 방향으로 진행한 적이 이미 있었는지 확인합니다.
        // 이미 한 번 겪었던 상황이라면, 탈출이 불가능 하다는 의미이므로 
        // -1을 출력하고 프로그램을 종료합니다.
        if(visited[currX][currY][currDir]) {
            System.out.print(-1);
            System.exit(0);
        }
        // 현재 상황이 다시 반복되는지를 나중에 확인하기 위해
        // 현재 상황에 해당하는 곳에 visited 값을 true로 설정합니다.
        visited[currX][currY][currDir] = true;
    
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
    
        int nextX = currX + dx[currDir];
        int nextY = currY + dy[currDir];
    
        // Step1
        
        // 바라보고 있는 방향으로 이동하는 것이 불가능한 경우에는
        // 반 시계 방향으로 90' 방향을 바꿉니다.
        if(wallExist(nextX, nextY))
            currDir = (currDir - 1 + 4) % 4;
        
        // Step2
        
        // Case1
        // 바라보고 있는 방향으로 이동하는 것이 가능한 경우 중
        // 바로 앞이 격자 밖이라면 탈출합니다.
        else if(!inRange(nextX, nextY)) {
            currX = nextX; currY = nextY;
            elapsedTime++;
        }
    
        // Case2 & Case3
        // 바로 앞이 격자 안에서 이동할 수 있는 곳이라면 
        else {
            // 그 방향으로 이동했다 가정했을 때 바로 오른쪽에 짚을 벽이 있는지 봅니다.
            int rx = nextX + dx[(currDir + 1) % 4];
            int ry = nextY + dy[(currDir + 1) % 4];
    
            // Case2
            // 그대로 이동해도 바로 오른쪽에 짚을 벽이 있다면 
            // 해당 방향으로 한 칸 이동합니다.
            if(wallExist(rx, ry)) {
                currX = nextX; currY = nextY;
                elapsedTime++;
            }
    
            // Case3
            // 그렇지 않다면 2칸 이동후 방향을 시계방향으로 90' 방향을 바꿉니다.
            else {
                currX = rx; currY = ry;
                currDir = (currDir + 1) % 4;
                elapsedTime += 2;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        currX = sc.nextInt();
        currY = sc.nextInt();
        
        // 처음에는 우측 방향을 바라보고 시작합니다.
        currDir = 0;

        for(int i = 1; i <= n; i++) {
            String str = sc.next();
            for(int j = 1; j <= n; j++)
                a[i][j] = str.charAt(j - 1);
        }
        
        do {
            // 조건에 맞춰 움직여봅니다.
            simulate();
        
        // 격자를 빠져나오기 전까지 계속 반복합니다.
        } while(inRange(currX, currY)); 

        // 출력:
        System.out.print(elapsedTime);
    }
}