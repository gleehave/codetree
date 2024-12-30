import java.util.Scanner;

public class Main {

    static class Node{
        String data;
        Node prev, next;

        public Node(String data){
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    public static void connect(Node s, Node e){
        if (s != null) s.next = e;
        if (e != null) e.prev = s;
    }

    public static void insertNext(Node target, Node s){
        connect(s, target.next);
        connect(target, s);
    }

    public static void insertPrev(Node target, Node s){
        connect(target.prev, s);
        connect(s, target);
    }

    public static void printNode(Node target){
        String n = "(Null)";
        
        if (target.prev == null) System.out.print(n + " ");
        else System.out.print(target.prev.data +" ");
        
        System.out.print(target.data + " ");

        if (target.next == null) System.out.println(n);
        else System.out.println(target.next.data);
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String sInit = sc.nextLine();
        Node cur = new Node(sInit);

        int n = sc.nextInt();
        while(n-- > 0){
            int option = sc.nextInt();
            if (option == 1){
                String data = sc.next();
                Node target = new Node(data);
                insertPrev(cur, target);
            }

            if (option == 2){
                String data = sc.next();
                Node target = new Node(data);
                insertNext(cur, target);
            }

            if (option == 3){
                if (cur.prev != null) cur = cur.prev;
            }

            if (option == 4){
                if (cur.next != null) cur = cur.next;
            }
            printNode(cur);
        }
        sc.close();
    }
}