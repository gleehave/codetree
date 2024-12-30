import java.io.*;
import java.util.*;

class Node {
    int data;
    Node prev, next;

    public Node(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

public class Main {
    static int n, q;
    static Node[] nodes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        q = Integer.parseInt(br.readLine());

        // 노드 초기화
        nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }

        while (q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int i, j;

            switch (command) {
                case 1: // 단일 노드로 분리
                    i = Integer.parseInt(st.nextToken());
                    pop(nodes[i]);
                    break;

                case 2: // i 앞에 j 삽입
                    i = Integer.parseInt(st.nextToken());
                    j = Integer.parseInt(st.nextToken());
                    insertFront(nodes[i], nodes[j]);
                    break;

                case 3: // i 뒤에 j 삽입
                    i = Integer.parseInt(st.nextToken());
                    j = Integer.parseInt(st.nextToken());
                    insertRear(nodes[i], nodes[j]);
                    break;

                case 4: // 이전, 다음 노드 출력
                    i = Integer.parseInt(st.nextToken());
                    output.append(getPrevNext(nodes[i])).append("\n");
                    break;
            }
        }

        // 1번부터 N번까지 다음 노드 출력
        for (int i = 1; i <= n; i++) {
            output.append(nodes[i].next != null ? nodes[i].next.data : 0).append(" ");
        }

        System.out.println(output.toString().trim());
    }

    // 노드를 단일 노드로 분리
    static void pop(Node node) {
        if (node.prev != null) node.prev.next = node.next;
        if (node.next != null) node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }

    // i 앞에 j 삽입
    static void insertFront(Node i, Node j) {
        if (j.next != null || j.prev != null) return; // j는 단일 노드여야 함

        if (i.prev != null) i.prev.next = j;
        j.prev = i.prev;
        j.next = i;
        i.prev = j;
    }

    // i 뒤에 j 삽입
    static void insertRear(Node i, Node j) {
        if (j.next != null || j.prev != null) return; // j는 단일 노드여야 함

        if (i.next != null) i.next.prev = j;
        j.next = i.next;
        j.prev = i;
        i.next = j;
    }

    // 이전 노드와 다음 노드의 번호를 반환
    static String getPrevNext(Node node) {
        int prev = (node.prev != null) ? node.prev.data : 0;
        int next = (node.next != null) ? node.next.data : 0;
        return prev + " " + next;
    }
}