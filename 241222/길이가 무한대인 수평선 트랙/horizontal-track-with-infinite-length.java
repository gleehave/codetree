import java.util.Scanner;
import java.util.TreeSet;

class Person implements Comparable<Person> {
    int x, v;

    public Person(int x, int v) {
        this.x = x;
        this.v = v;
    }

    // x 기준 오름차순 정렬
    @Override
    public int compareTo(Person p) {
        return this.x - p.x;
    }
}

class Event implements Comparable<Event> {
    double currT;
    int x, v;

    public Event(double currT, int x, int v) {
        this.currT = currT;
        this.x = x;
        this.v = v;
    }

    // currT 기준 오름차순 정렬
    @Override
    public int compareTo(Event e) {
        double diff = this.currT - e.currT;
        if(diff < 0)
            return -1;
        else if(diff == 0)       // 값이 동일한 경우 x 기준으로 정렬하여
            return this.x - e.x; // 같은 값으로 처리되지 않도록 해야함
        else
            return 1;
    }
}

public class Main {    
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n, t;
    // x 순으로 정렬하여 사람들을 관리합니다.
    public static TreeSet<Person> peopleX = new TreeSet<>();
    // 인접한 사람끼리 만나는 시간 순으로 정렬하여 사건들을 관리합니다.
    public static TreeSet<Event> eventT = new TreeSet<>();  
    
    public static int[] x = new int[MAX_N];
    public static int[] v = new int[MAX_N];
    
    // (x1, v1) 사람이 바로 앞에 있는 (x2, v2)와 마주치는 데 걸리는 시간
    // 정보를 얻어 사건 정보를 추가합니다. 
    public static void addEvent(int x1, int v1, int x2, int v2) {
        // 절대 따라잡을 수 없는 경우라면 무시합니다.
        if(v1 <= v2)
            return;
        
        eventT.add(new Event(1.0 * (x2 - x1) / (v1 - v2), x1, v1));
    }
    
    // (x1, v1) 사람이 바로 앞에 있는 (x2, v2)와 마주치는 데 걸리는 시간
    // 정보를 얻어 해당 사건 정보를 제거합니다.
    public static void removeEvent(int x1, int v1, int x2, int v2) {
        // 절대 따라잡을 수 없는 경우라면 무시합니다.
        if(v1 <= v2)
            return;
        
        eventT.remove(new Event(1.0 * (x2 - x1) / (v1 - v2), x1, v1));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        t = sc.nextInt();
        for(int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }
        
        // 사람들을 set으로 관리합니다.
        for(int i = 0; i < n; i++)
            peopleX.add(new Person(x[i], v[i]));

        // 인접한 사람끼리 만나는 사건도 set으로 관리합니다.
        // (t, x, v) : 
        // 현재 x 위치에서 속도 v로 이동하는 사람과
        // 바로 앞에 있는 사람이 
        // 마주치는 데 거리는 시간 t라는 정보 기입
        for(int i = 0; i < n - 1; i++)
            addEvent(x[i], v[i], x[i + 1], v[i + 1]);

        // 앞지르는 사건이 존재한다면 반복합니다.
        while(!eventT.isEmpty()) {
            Event e = eventT.first();
            double currT = e.currT; 
            int x = e.x, v = e.v;

            // 이미 t분이 넘었다면 종료합니다.
            if(currT > t)
                break;

            // 해당 사람과 사건을 삭제합니다.
            peopleX.remove(new Person(x, v));
            eventT.remove(new Event(currT, x, v));
            
            // 바로 앞 사람 위치를 구합니다.
            Person np = peopleX.higher(new Person(x, v));
            int nx = np.x, nv = np.v;

            // 바로 뒤에 사람이 있다면 
            // 이전 사건을 삭제하고
            // 새로운 사건을 추가합니다.
            if(peopleX.lower(np) != null) {
                Person pp = peopleX.lower(np);
                int px = pp.x, pv = pp.v;
                removeEvent(px, pv, x, v);
                addEvent(px, pv, nx, nv);
            }
        }

        // 서로 다른 그룹의 수를 출력합니다.
        System.out.print(peopleX.size());
    }
}