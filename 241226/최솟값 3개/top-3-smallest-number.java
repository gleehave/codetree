import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Main {
    static int n;
    static PriorityQueue<Long> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            pq.offer(Long.parseLong(st.nextToken()));

            if (pq.size() <= 2){
                System.out.println(-1);
                continue;
            }
            long a = pq.poll();
            long b = pq.poll();
            long c = pq.poll();

            System.out.println(a * b * c);

            pq.offer(a);
            pq.offer(b);
            pq.offer(c);
        }
    }
}