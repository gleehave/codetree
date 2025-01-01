import java.util.Scanner;

public class Main {

    static class Node {
        String data;
        Node prev, next;

        Node(String data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    // 두 도시를 연결해줍니다.
    private static void connect(Node s, Node e) {
        if (s != null) s.next = e;
        if (e != null) e.prev = s;
    }

    // target 뒤에 s를 삽입합니다.
    private static void insertNext(Node target, Node s) {
        connect(s, target.next);
        connect(target, s);
    }

    // 해당 도시를 삭제합니다.
    private static void pop(Node u) {
        connect(u.prev, u.next);
        u.prev = u.next = null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력을 받습니다.
        int n = sc.nextInt();
        int q = sc.nextInt();
        Node pin = null, prev = null;

        // 원형으로 연결을 해야합니다.
        for (int i = 1; i <= n; i++) {
            String x = sc.next();
            Node target = new Node(x);
            // 맨 처음 도시가 핀셋으로 꽂혀 있는 도시입니다.
            if (i == 1) {
                pin = target;
            } else {
                connect(prev, target);
            }

            // 원형이기 때문에 맨 마지막 도시와 핀셋으로 꽂혀 있는 도시를 연결해줍니다.
            if (i == n) connect(target, pin);

            prev = target;
        }

        // 각 질의를 수행합니다.
        while (q-- > 0) {
            int option = sc.nextInt();

            if (option == 1) {
                if (pin.next != null) pin = pin.next;
            }

            if (option == 2) {
                if (pin.prev != null) pin = pin.prev;
            }

            if (option == 3) {
                if (pin.next != pin)
                    pop(pin.next);
            }

            if (option == 4) {
                String x = sc.next();
                insertNext(pin, new Node(x));
            }

            // 조건에 맞게 출력해줍니다.
            if (pin.next == pin.prev || pin.next == null || pin.prev == null) System.out.println(-1);
            else System.out.println(pin.prev.data + " " + pin.next.data);
        }

        sc.close();
    }
}