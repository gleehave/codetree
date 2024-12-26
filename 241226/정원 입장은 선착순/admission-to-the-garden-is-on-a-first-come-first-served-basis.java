import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Arrays;

class EnteringPerson implements Comparable<EnteringPerson> {
    int a, num, t;

    public EnteringPerson(int a, int num, int t) {
        this.a = a;
        this.num = num;
        this.t = t;
    }

    @Override
    public int compareTo(EnteringPerson ep) {
        if(this.a != ep.a)                  // 정원에 먼저 도착한 사람부터 나오도록
            return this.a - ep.a;           // a 기준으로 오름차순 정렬해줍니다.
        return this.num - ep.num;           // 도착시간이 동일하다면 번호가 작은 사람이 먼저 나오도록 합니다.
    }                                       
}

class WaitingPerson implements Comparable<WaitingPerson> {
    int num, a, t;

    public WaitingPerson(int num, int a, int t) {
        this.num = num;
        this.a = a;
        this.t = t;
    }

    @Override
    public int compareTo(WaitingPerson wp) {
        return this.num - wp.num;             // 번호가 작은 학생이 먼저 나오도록 
    }                                         // 번호를 기준으로 오름차순 정렬해줍니다.
}

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n;
    public static EnteringPerson[] people = new EnteringPerson[MAX_N + 1];
    public static PriorityQueue<WaitingPerson> pq = new PriorityQueue<>();  
    
    public static int ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int t = sc.nextInt();

            // 이후 정원에 먼저 도달한 사람이
            // 앞에 나올 수 있도록 a, 번호, t 순으로 넣어줍니다.
            // 두 번째 위치에
            // 번호를 넣어주는 이유는
            // 나중에 여러 사람이 기다릴 때
            // 가장 번호가 작은 사람을 뽑기 위해서 입니다.
            people[i] = new EnteringPerson(a, i + 1, t); 
        }

        // 구현 편의상 마지막 사람을 한 명 더 추가해줍니다.
        people[n] = new EnteringPerson(INT_MAX, n + 1, 0);

        // 정렬을 진행합니다.
        Arrays.sort(people, 0, n + 1);

        // 정원에 도착한 순서대로 사람들을 보며
        // 현재 정원에 있는 사람이 언제 나오게 되는지를 계속 계산해줍니다.
        // 이때 우선순위 큐를 이용하여 기다리고 있는 사람들의 정보를 관리하여
        // 정원에서 사람이 나온 즉시 기다리던 사람 중 
        // 번호가 가장 작은 학생이 바로 들어갈 수 있도록 합니다.
        int exitTime = 0;
        for(int i = 0; i <= n; i++) {
            // 각 사람을 순서대로 입장시킵니다.
            int a = people[i].a;
            int num = people[i].num;
            int t = people[i].t;

            // 지금 입장한 사람보다
            // 현재 정원에서 빠져나오는 사람의 시간이 더 앞서다면
            // 계속 정원 입장을 진행해줍니다.
            while(a > exitTime && !pq.isEmpty()) {
                // 기다리던 사람 중에 가장 우선순위가 높은 사람을 골라줍니다.
                WaitingPerson nextWP = pq.poll();
                int nextA = nextWP.a;
                int nextT = nextWP.t;

                // 해당 사람이 얼마나 기다렸는지를 계산하여
                // 최댓값을 갱신해줍니다.
                ans = Math.max(ans, exitTime - nextA);
                // 연속하여 일어난 일이므로
                // 그 다음 사람의 정원 퇴장 시간은
                // nextT만큼 더해진 값이 됩니다.
                exitTime += nextT;
            }

            // 계속 정원 입장을 진행했음에도
            // 지금 입장한 사람이 대기 없이 들어갈 수 있다면
            // 우선순위 큐에 넣지 않고 바로 정원에 입장시킵니다.
            if(a > exitTime)
                exitTime = a + t;
            // 그렇지 않다면
            // 대기 리스트에 넣어줍니다.
            else
                pq.add(new WaitingPerson(num, a, t));
        }

        System.out.print(ans);
    }
}