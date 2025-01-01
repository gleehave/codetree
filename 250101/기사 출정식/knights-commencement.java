import java.io.*;
import java.util.*;

class Node{

    int id;
    Node prev;
    Node next;

    public Node(int id){
        this.id = id;
        this.prev = null;
        this.next = null;
    }
}

public class Main {

    static int n, m;
    static Node[] nodes;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        nodes = new Node[n];
        for(int i=0; i<n; i++){
            nodes[i] = new Node(Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<n-1; i++){
            connect(nodes[i], nodes[i+1]);
        }

        // 원형
        nodes[0].prev = nodes[n-1];
        nodes[n-1].next = nodes[0];
        

        while(m-- > 0){
            st = new StringTokenizer(br.readLine());
            int callId = Integer.parseInt(st.nextToken());

            Node target = null;
            for(int i=0; i<n; i++){
                if (nodes[i].id == callId){
                    target = nodes[i];
                    break;
                } 
            }

            System.out.println(target.next.id +" "+target.prev.id);
            
            target.prev.next = target.next;
            target.next.prev = target.prev;   
        }
    }

    public static void connect(Node a, Node b){
        if (a != null) a.next = b;
        if (b != null) b.prev = a;
    }
}