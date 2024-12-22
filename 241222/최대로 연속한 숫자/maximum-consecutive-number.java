import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int n, m;
    static int[] dp, arr;

    static TreeSet<Integer> treeSet = new TreeSet<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=0; i<=n; i++){
            treeSet.add(i);
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++){
            treeSet.remove(Integer.parseInt(st.nextToken()));
            System.out.println(countLength());
        }
    }

    public static int countLength(){
        dp = new int[treeSet.size()];
        arr = new int[treeSet.size()];

        int index = 0;
        for(int n : treeSet) arr[index++] = n;

        Arrays.fill(dp, 1);
        for(int i=0; i<treeSet.size(); i++){
            for(int j=0; j<i; j++){
                if (arr[j] + 1 == arr[j+1]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                } else {
                    dp[i] = 1;
                }
            }
        }

        int max = -1;
        for(int n : dp) max = Math.max(max, n);

        return max;
    }
}