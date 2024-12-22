import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    public static final int MAX_G = 250000;
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n, g;
    public static boolean[] invited = new boolean[MAX_N];
    // 각 그룹마다 초대장을 받지 못한 사람들을 관리해줍니다.
    public static HashSet<Integer>[] groups = new HashSet[MAX_G];
    // 각 사람이 어떤 그룹에 속하는지를 관리해줍니다.
    public static ArrayList<Integer>[] peopleGroups = new ArrayList[MAX_N];
    public static Queue<Integer> q = new LinkedList<>();
    public static int ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        g = sc.nextInt();

        for(int i = 0; i < n; i++)
            peopleGroups[i] = new ArrayList<>();

        for(int i = 0; i < g; i++)
            groups[i] = new HashSet<>();

        for(int i = 0; i < g; i++) {
            int s = sc.nextInt();
            for(int j = 0; j < s; j++) {
                int x = sc.nextInt(); x--;
                groups[i].add(x);
                peopleGroups[x].add(i);
            }
        }

        q.add(0);
        invited[0] = true;
        while(!q.isEmpty()) {
            int x = q.poll();
            ans++;

            // x가 들어있는 그룹에서 x를 지웁니다.
            // hashset에는 그룹에서 초대받지 않은 인원만을 남깁니다.
            for(int i = 0; i < peopleGroups[x].size(); i++) {
                int gNum = peopleGroups[x].get(i);

                // 해당 그룹에서 x를 지웁니다.
                groups[gNum].remove(x);
                // 초대받지 않은 인원이 한명밖에 없다면 초대합니다.
                if(groups[gNum].size() == 1) {
                    int pNum = new ArrayList<>(groups[gNum]).get(0);
                    if(!invited[pNum]) {
                        invited[pNum] = true;
                        q.add(pNum);
                    }
                }
            }
        }

        // 초대장을 받는 인원을 출력합니다.
        System.out.print(ans);
    }
}