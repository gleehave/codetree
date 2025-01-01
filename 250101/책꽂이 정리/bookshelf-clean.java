import java.util.*;

public class Main {
    public static final int MAX_K = 100;

    // 각 연결 리스트를 LinkedList로 관리
    public static LinkedList<Integer>[] lists = new LinkedList[MAX_K + 1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 노드의 개수
        int k = sc.nextInt(); // 연결 리스트의 개수

        // LinkedList 초기화
        for (int i = 1; i <= k; i++) {
            lists[i] = new LinkedList<>();
        }

        // 1번 리스트에 1부터 n까지 초기화
        for (int i = 1; i <= n; i++) {
            lists[1].add(i);
        }

        int q = sc.nextInt(); // 쿼리 개수

        for (int t = 0; t < q; t++) {
            int type = sc.nextInt();
            int i = sc.nextInt();
            int j = sc.nextInt();

            if (type == 1) {
                // i번 리스트의 맨 앞 원소를 j번 리스트의 맨 뒤로 이동
                if (!lists[i].isEmpty()) {
                    int value = lists[i].removeFirst();
                    lists[j].addLast(value);
                }
            } else if (type == 2) {
                // i번 리스트의 맨 뒤 원소를 j번 리스트의 맨 앞으로 이동
                if (!lists[i].isEmpty()) {
                    int value = lists[i].removeLast();
                    lists[j].addFirst(value);
                }
            } else if (type == 3) {
                // i번 리스트를 j번 리스트의 맨 앞에 병합
                if (!lists[i].isEmpty()) {
                    LinkedList<Integer> temp = new LinkedList<>(lists[i]);
                    lists[i].clear(); // i번 리스트를 비움
                    temp.addAll(lists[j]); // i번 리스트를 j번 리스트의 맨 앞에 추가
                    lists[j] = temp; // 새로운 j번 리스트로 설정
                }
            } else if (type == 4) {
                // i번 리스트를 j번 리스트의 맨 뒤에 병합
                if (!lists[i].isEmpty()) {
                    lists[j].addAll(lists[i]);
                    lists[i].clear(); // i번 리스트를 비움
                }
            }
        }

        // 각 리스트 출력
        for (int i = 1; i <= k; i++) {
            int cnt = lists[i].size(); // 리스트의 크기
            System.out.print(cnt);
            for (int value : lists[i]) {
                System.out.print(" " + value);
            }
            System.out.println();
        }
    }
}