import java.io.*;
import java.util.StringTokenizer;
import java.util.HashSet;


public class Main {
    static int n, m;
    static int[] arr1, arr2;
    static HashSet<Integer> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            set.add(Integer.parseInt(st.nextToken()));
        }

        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++){
            int value = Integer.parseInt(st.nextToken());
            if (set.contains(value)){
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}