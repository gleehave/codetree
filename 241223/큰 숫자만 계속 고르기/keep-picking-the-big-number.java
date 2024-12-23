import java.io.*;
import java.util.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Main {

    static int n, m;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
        @Override
        public int compare(Integer a, Integer b){
            return b - a;
        }
    });

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            pq.offer(Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<m; i++){
            int value = pq.poll();
            value -= 1;
            pq.offer(value);
        }

        System.out.println(pq.poll());
    }
}