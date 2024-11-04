import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static List<Integer> result = new ArrayList<>();
    static boolean[] used;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        used = new boolean[n+1]; // 1~8까지 사용한다.

        permutation(0);
    }

    public static void permutation(int depth){
        if (depth == n){
            for(int i=0; i<result.size(); i++){
                System.out.print(result.get(i)+" ");
            }
            System.out.println();
            return;
        }

        for(int i=1; i<=n; i++){
            if (used[i]) continue;

            used[i] = true;
            result.add(i);
            permutation(depth+1);

            result.remove(result.size()-1);
            used[i] = false;
        }
    }
}