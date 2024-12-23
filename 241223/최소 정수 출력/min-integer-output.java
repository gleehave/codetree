import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Main {
    static int n;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            int value = Integer.parseInt(br.readLine());
            if (value == 0){
                if (pq.isEmpty()){
                    System.out.println(0);
                } else {
                    System.out.println(pq.peek());
                }
                pq.poll();
            } else {
                pq.offer(value);
            }
        }
    }
}