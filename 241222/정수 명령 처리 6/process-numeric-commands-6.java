import java.io.*;
import java.util.*;
import java.util.PriorityQueue;

public class Main {
    static int n;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch(command){
                case "push":
                    pq.offer(-1 * Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    System.out.println(-1 * pq.poll());
                    break;
                case "size":
                    System.out.println(pq.size());
                    break;
                case "empty":
                    if (pq.isEmpty()) System.out.println(1);
                    else System.out.println(0);
                    break;
                case "top":
                    System.out.println(-1 * pq.peek());
                    break;                                                                                
            }
        }
    }
}