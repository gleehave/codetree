import java.io.*;
import java.util.*;

public class Main {
    static int n, g; // n: 사람 수, g: 그룹 수
    static List<List<Integer>> groups; // 그룹 정보를 저장
    static boolean[] visited; // 방문 여부 확인용

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());

        groups = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            groups.add(new ArrayList<>());
        }

        // 그룹 정보 입력 받기
        for (int i = 0; i < g; i++) {
            st = new StringTokenizer(br.readLine());
            int groupSize = Integer.parseInt(st.nextToken());
            int[] members = new int[groupSize];
            for (int j = 0; j < groupSize; j++) {
                members[j] = Integer.parseInt(st.nextToken());
            }
            // 그룹 내 사람들 간의 연결 생성
            for (int j = 0; j < groupSize; j++) {
                for (int k = j + 1; k < groupSize; k++) {
                    groups.get(members[j]).add(members[k]);
                    groups.get(members[k]).add(members[j]);
                }
            }
        }

        // 1번 사람과 연결된 모든 사람 탐색
        visited = new boolean[n + 1];
        int result = bfs(1); // 1번 사람을 기준으로 BFS 수행

        System.out.println(result); // 확실히 초대장을 받는 사람 수 출력
    }

    // BFS로 연결된 모든 노드 탐색
    public static int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        int count = 0; // 초대장을 받는 사람 수

        while (!queue.isEmpty()) {
            int person = queue.poll();
            count++;

            for (int neighbor : groups.get(person)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return count;
    }
}
