import java.io.*;
import java.util.*;

class Node{
    int data;
    Node prev, next;

    public Node(int data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

public class Main {
    
    static void insertFront(Node i, Node j){
        if (i != null) i.prev = j;
        if (j != null) j.next = i;
    }

    static void insertRear(Node i, Node j){
        if (i != null) i.next = j;
        if (j != null) j.prev = i;
    }

    static void pop(Node i){
        if (i != null && i.next != null) i.next.prev = i.prev;
        if (i != null && i.prev != null) i.prev.next = i.next;

        i.next = null;
        i.prev = null;
    }

    static void printNode(Node i){
        if (i != null){
            if (i.prev != null) System.out.print(i.prev.data+" ");
            else System.out.print(0+" ");
            
            if (i.next != null) System.out.println(i.next.data+" ");
            else System.out.print(0+" ");
        }
        System.out.println();
    }

    static int n, q;
    static HashMap<Integer, Node> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        q = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++){
            map.put(i, new Node(i));
        }

        while(q-- > 0){
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int i, j;

            switch(command){
                case 1:
                    int key = Integer.parseInt(st.nextToken());
                    pop(map.get(key));
                    break;
                case 2:
                    i = Integer.parseInt(st.nextToken());
                    j = Integer.parseInt(st.nextToken());
                    insertFront(map.get(i), map.get(j));
                    break;
                case 3:
                    i = Integer.parseInt(st.nextToken());
                    j = Integer.parseInt(st.nextToken());
                    insertRear(map.get(i), map.get(j));
                    break;
                case 4:
                    i = Integer.parseInt(st.nextToken());
                    printNode(map.get(i));           
                    break;                                                            
            }
        } // while

        for(Integer key : map.keySet()){
            if (map.get(key).next != null) System.out.print(map.get(key).next.data+" ");
            else System.out.print(0+" ");
        }
    }
}