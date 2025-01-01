import java.io.*;
import java.util.*;

class Node {
    int id;
    Node prev;
    Node next;

    public Node(int id) {
        this.id = id;
        this.prev = null;
        this.next = null;
    }
}

public class Main {

    static int n, m;
    static Node[] nodes;
    static Map<Integer, Node> nodeMap;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        nodes = new Node[n];
        nodeMap = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(Integer.parseInt(st.nextToken()));
            nodeMap.put(nodes[i].id, nodes[i]); // id와 Node 매핑
        }

        // 연결 리스트 구성
        for (int i = 0; i < n - 1; i++) {
            connect(nodes[i], nodes[i + 1]);
        }

        // 원형 연결 리스트
        nodes[0].prev = nodes[n - 1];
        nodes[n - 1].next = nodes[0];

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int callId = Integer.parseInt(st.nextToken());

            // HashMap으로 Node 찾기
            Node target = nodeMap.get(callId);

            // 결과 출력
            System.out.println(target.next.id + " " + target.prev.id);

            // 연결 리스트에서 제거
            target.prev.next = target.next;
            target.next.prev = target.prev;

            // HashMap에서 제거
            nodeMap.remove(callId);
        }
    }

    public static void connect(Node a, Node b) {
        if (a != null) a.next = b;
        if (b != null) b.prev = a;
    }
}