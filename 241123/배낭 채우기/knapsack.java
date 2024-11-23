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

    public static void main(String[] args) throws Exception{
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        diamonds = new Diamond[n+1];
        dp = new int[m+1];

        diamonds[0] = new Diamond(0, 0);
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            diamonds[i] = new Diamond(weight, value);
        }

        for(int i=1; i<=m; i++){
            dp[i] = 0;
        }

        for(int i=1; i<=n; i++){

            for(int j=m; j>=0; j--){
                if (j - diamonds[i].weight < 0) continue;
                dp[j] = Math.max(dp[j], dp[j - diamonds[i].weight] + diamonds[i].value);
            }
        }

        System.out.println(dp[m]);

    }
}