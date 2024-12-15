import java.io.*;
import java.util.StringTokenizer;
import java.util.HashSet;

public class Main {
    static int n;
    static HashSet<Integer> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            set.add(Integer.parseInt(st.nextToken()));
        }

        System.out.println(set.size());
    }
}