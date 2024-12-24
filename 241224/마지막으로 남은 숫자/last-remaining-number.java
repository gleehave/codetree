import java.io.*;
import java.util.*;
import java.util.PriorityQueue;

public class Main {

    static int n;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
        @Override
        public int compare(Integer a, Integer b){
            return b - a;
        }
    });

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            pq.offer(Integer.parseInt(st.nextToken()));
        }
        
        while(true){
            if (pq.isEmpty() || pq.size() == 1){
                break;
            }
            int a = pq.poll();
            int b = pq.poll();
            int operation = Math.abs(a-b);

            if (operation == 0) continue;
            pq.offer(operation); 
        }
        
        if (pq.isEmpty()) System.out.println(-1);
        else System.out.println(pq.poll());
    }
}