import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static boolean[] number;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        number = new boolean[1000001];
        number[0] = true;
        System.out.println(solve());
    }

    public static int solve(){
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                return a[1] - b[1];
            }
        });

        queue.offer(new int[]{n, 0});

        while(!queue.isEmpty()){

            int[] cur = queue.poll();

            if (cur[0] == 1){
                return cur[1];
            }

            if (number[cur[0]]){
                continue;
            }
            number[cur[0]] = true;
            if (cur[0] - 1 >= 1) queue.offer(new int[]{cur[0] - 1, cur[1]+1});
            if (cur[0] + 1 <= 1000000) queue.offer(new int[]{cur[0] + 1, cur[1]+1});
            if (cur[0] % 2 == 0) queue.offer(new int[]{cur[0] / 2, cur[1]+1});
            if (cur[0] % 3 == 0) queue.offer(new int[]{cur[0] / 3, cur[1]+1});
        }
        return -1;
    }
}