import java.io.*;
import java.util.*;
import java.util.HashSet;

public class Main {
    static int n, m;
    static int[] arr1, arr2;
    static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        n = Integer.parseInt(br.readLine());
        arr1 = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr1[i] = Integer.parseInt(st.nextToken());
            set.add(arr1[i]);
        }

        m = Integer.parseInt(br.readLine());
        arr2 = new int[m];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++){
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<m; i++){
            if (set.contains(arr2[i])){
                System.out.print(1 +" ");
            } else {
                System.out.print(0 +" ");
            }
        }

    }
}