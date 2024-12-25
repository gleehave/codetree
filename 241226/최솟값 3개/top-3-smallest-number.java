import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Main {
    static int n;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            pq.offer(Integer.parseInt(st.nextToken()));

            if (pq.size() <= 2){
                System.out.println(-1);
                continue;
            }
            int a = pq.poll();
            int b = pq.poll();
            int c = pq.poll();
            System.out.println(a * b * c);

            pq.offer(a);
            pq.offer(b);
            pq.offer(c);
        }
    }
}