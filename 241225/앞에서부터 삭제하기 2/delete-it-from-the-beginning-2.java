import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Main {
    static int n;
    static int[] arr;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        float max = -1;
        int k = 0;
        while(k < n){
            pq = new PriorityQueue<>();

            for(int i=k+1; i<n; i++){
                pq.offer(arr[i]);
            }

            pq.poll();

            int sum = 0;
            for(int num: pq) sum += num;

            if (pq.size() != 0){
                float res = (float)(sum / pq.size());
                max = Math.max(max, res);
            } 
            k++;
        }

        System.out.printf("%.2f", max);
    }
}