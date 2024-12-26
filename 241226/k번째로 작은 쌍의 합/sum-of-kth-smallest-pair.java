import java.io.*;
import java.util.*;

public class Main {

    static class Pair implements Comparable<Pair> {
        int sum, i, j;

        public Pair(int sum, int i, int j) {
            this.sum = sum;
            this.i = i;
            this.j = j;
        }

        @Override
        public int compareTo(Pair other) {
            return Integer.compare(this.sum, other.sum);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr1 = new int[n];
        int[] arr2 = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        PriorityQueue<Pair> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            pq.offer(new Pair(arr1[i] + arr2[0], i, 0));
        }

        int result = 0;
        for (int t = 0; t < k; t++) {
            Pair current = pq.poll();
            result = current.sum;
            int i = current.i, j = current.j;

            if (j + 1 < m) {
                pq.offer(new Pair(arr1[i] + arr2[j + 1], i, j + 1));
            }
        }

        System.out.println(result);
    }
}
