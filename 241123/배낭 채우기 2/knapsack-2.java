import java.io.*;
import java.util.*;

class Diamond{
    int weight, value;

    public Diamond(int weight, int value){
        this.weight = weight;
        this.value = value;
    }
}

public class Main {

    static int n, m;
    static Diamond[] diamonds;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        diamonds = new Diamond[n+1];
        dp = new int[m+1];

        diamonds[0] = new Diamond(0, 0); // weight, value
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            diamonds[i] = new Diamond(weight, value);
        }

        for(int i=1; i<=m; i++){
            dp[i] = 0;
        }

        for(int i=1; i<=m; i++){ // 무게
            for(int j=0; j<=n; j++){ // 보석판단
                if (i < diamonds[j].weight) continue;
                dp[i] = Math.max(dp[i], dp[i - diamonds[j].weight] + diamonds[j].value);
            }
        }
        System.out.println(dp[m]);
    }
}