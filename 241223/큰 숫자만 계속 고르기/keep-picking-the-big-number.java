import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Main {

    static int n, m;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            pq.offer(-1 * Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<m; i++){
            int max = pq.poll();
            if (max < 0) max += 1;
            else if (max == 0) max -= 1;
            else max -= 1;
            pq.offer(max);
        }

        int res = pq.poll();
        System.out.println(-1 * res);
    }
}