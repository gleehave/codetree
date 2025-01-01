import java.util.Scanner;

public class Main {
    public static final int MAX_N = 250005;

    // 한 노드를 나타내는 클래스입니다.
    static class Node {
        int id;
        Node prev, next;

        Node(int id) {
            this.id = id;
            this.prev = null;
            this.next = null;
        }
    }

    public static Node[] nodes = new Node[MAX_N];

    // 두 노드를 연결해줍니다.
    public static void connect(Node s, Node e) {
        if (s != null)
            s.next = e;
        if (e != null)
            e.prev = s;
    }

    // 두 노드 사이의 부분 배열을 서로 바꾸는 함수입니다.
    public static void swapSubarray(Node a, Node b, Node c, Node d) {
        // 각 노드의 이전 노드와 다음 노드의 연결 상태를 저장합니다.
        Node after_prevA = c.prev; // a와 연결된 c의 이전 노드
        Node after_nextB = d.next; // b와 연결된 d의 다음 노드

        Node after_prevC = a.prev; // c와 연결된 a의 이전 노드
        Node after_nextD = b.next; // d와 연결된 b의 다음 노드

        // b와 c가 바로 인접한 경우, 즉 b 다음에 c가 오는 경우의 예외 처리를 합니다.
        if (b.next == c) {
            after_prevA = d;
            after_nextD = a;
        }
        // d와 a가 바로 인접한 경우, 즉 d 다음에 a가 오는 경우의 예외 처리를 합니다.
        if (d.next == a) {
            after_nextB = c;
            after_prevC = b;
        }

        // 노드들을 새로운 연결 상태에 맞게 연결해줍니다.
        connect(after_prevA, a);
        connect(b, after_nextB);

        connect(after_prevC, c);
        connect(d, after_nextD);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // N개의 노드를 생성합니다.
        for (int i = 1; i <= n; i++)
            nodes[i] = new Node(i);

        // 1부터 N번 까지의 노드를 차례로 연결해줍니다.
        for (int i = 1; i < n; i++)
            connect(nodes[i], nodes[i + 1]);

        int q = sc.nextInt();

        // 연산을 진행합니다.
        for (int i = 0; i < q; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int d = sc.nextInt();

            swapSubarray(nodes[a], nodes[b], nodes[c], nodes[d]);
        }

        // 연산이 끝나고 제일 앞에 있는 노드를 찾습니다.
        Node cur = nodes[1];
        while (cur.prev != null)
            cur = cur.prev;

        // 해당 노드부터 끝까지 출력을 합니다.
        while (cur != null) {
            System.out.print(cur.id + " ");
            cur = cur.next;
        }
    }
}