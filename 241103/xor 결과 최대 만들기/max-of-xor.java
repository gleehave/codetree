import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] number;
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        number = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, 0);
    }

    public static void solve(int depth, int cur){
        if (depth == m){
            return;
        }

        for(int i=cur+1; i<number.length ;i++){
            result.add(number[i]);
            solve(depth+1, i);
            result.remove(result.size()-1);
        }
    }
}