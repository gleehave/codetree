import java.util.Scanner;
import java.util.ArrayList;

class Pair { 
    int x, y;
    public Pair(int x, int y) { 
        this.x = x; 
        this.y = y; 
    } 
}

public class Main {
    public static final int ASCII_NUM = 128;
    public static final int MAX_NUM = 100;
    
    public static int n, m, K;
    public static boolean[][] apple = new boolean[MAX_NUM + 1][MAX_NUM + 1];

    public static ArrayList<Pair> snake = new ArrayList<>();
    
    public static int[] dx = new int[]{1, -1, 0, 0};
    public static int[] dy = new int[]{0, 0, 1, -1};
    
    public static int[] mapper = new int[ASCII_NUM];
    
    public static int ans;
    
    // (x, y)가 범위 안에 들어가는지 확인합니다. 
    public static boolean canGo(int x, int y) {
        return x >= 1 && y >= 1 && x <= n && y <= n;
    }
    
    // 뱀이 꼬였는지 확인합니다.
    public static boolean isTwisted(Pair newHead) {
        // 뱀이 꼬였는지 여부는
        // 새로 들어올 머리가 기존 뱀의 몸통과 부딪히는지만 확인하면 됩니다.
        
         // 머리와 그 부분이 겹치는 경우에는
        // true 값을 반환해줍니다.
        for(int i = 0; i < (int) snake.size(); i++)
            if(newHead.x == snake.get(i).x && newHead.y == snake.get(i).y)                        
                return true;                                
        
         // 겹치지 않는 경우에는 false를 반환합니다.
        return false; 
    }
    
    // 새로운 머리를 추가합니다.
    public static boolean pushFront(Pair newHead) {
        // 몸이 꼬이는 경우
        // false를 반환합니다.
        if(isTwisted(newHead) == true)                        
            return false;                                     
        
        // 새로운 머리를 추가합니다.
        snake.add(0, newHead);                
    
        // 정상적으로 머리를 추가헀다는 의미로
        // true를 반환합니다.
        return true;                                         
    }                                                         
    
    // 꼬리를 지웁니다.
    public static void popBack() {
        snake.remove(snake.size() - 1);                                    
    }
    
    // (nx, ny) 쪽으로 뱀을 움직입니다.
    public static boolean moveSnake(int nx, int ny) {
        // 머리가 이동할 자리에 사과가 존재하면
        // 사과는 사라지게 되고
        if(apple[nx][ny] == true) {                           
            apple[nx][ny] = false;
            // 꼬리는 사라지지 않고 머리만 늘어납니다.
            // 늘어난 머리때문에 몸이 꼬이게 된다면
            // false를 반환합니다.
            if(pushFront(new Pair(nx, ny)) == false)         
                return false;                                 
        }                                                     
        else {
            // 사과가 없으면 꼬리는 사라지게 되고
            popBack();
            
            // 머리는 늘어나게 됩니다.
            // 늘어난 머리때문에 몸이 꼬이게 된다면
            // false를 반환합니다.
            if(pushFront(new Pair(nx, ny)) == false)         
                return false;                                 
        }       
        
        // 정상적으로 뱀이 움직였으므로
        // true를 반환합니다.
        return true;                                         
    }                                                         
    
    // 뱀을 dir 방향으로 num 번 움직입니다.
    public static boolean move(int dir, int num) {
        // num 횟수만큼 뱀을 움직입니다.
        // 한 번 움직일때마다 답을 갱신합니다.
        while(num-- > 0) {                              
            ans++;                                  
    
            Pair head = snake.get(0); 
    
            // 뱀의 머리가 그다음으로 움직일
            // 위치를 구합니다.
            int nx = head.x + dx[dir];          
            int ny = head.y + dy[dir];         
    
            // 그 다음 위치로 갈 수 없다면
            // 게임을 종료합니다.
            if(canGo(nx, ny) == false)              
                return false;                       
    
            // 뱀을 한 칸 움직입니다.
            // 만약 몸이 꼬인다면 false를 반환합니다.
            if(moveSnake(nx, ny) == false)          
                return false;                       
        }
        
        // 정상적으로 명령을 수행했다는 의미인 true를 반환합니다.
        return true;                               
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력으로 주어진 방향을 정의한 dx, dy에 맞도록 
        // 변환하는데 쓰이는 배열을 정의합니다.
        mapper['D'] = 0;
        mapper['U'] = 1;
        mapper['R'] = 2;
        mapper['L'] = 3;
        
        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();
        K = sc.nextInt();
        
        // 사과가 있는 위치를 표시합니다.
        for(int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            apple[x][y] = true;
        }

        // 뱀은 처음에 (1, 1)에서 길이 1의 상태로 있습니다.
        snake.add(new Pair(1, 1));

        // K개의 명령을 수행합니다.
        for(int i = 0; i < K; i++) {
            // dir 방향으로 num 횟수 만큼 움직여야 합니다.
            char dir = sc.next().charAt(0);
            int num = sc.nextInt();

            // 움직이는 도중 게임이 종료되었을 경우
            // 더 이상 진행하지 않습니다.
            if(move(mapper[dir], num) == false) 
                break;                         
        }

        // 출력:
        System.out.print(ans);
    }
}