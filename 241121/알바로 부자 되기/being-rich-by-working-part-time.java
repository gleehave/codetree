import java.io.*;
import java.util.*;

class Job implements Comparable<Job>{
    int s, e, p;

    public Job(int s, int e, int p){
        this.s = s;
        this.e = e;
        this.p = p;
    }

    public int compareTo(Job b){
        if (this.s == b.s) return this.e - b.e;
        return this.s - b.s;
    }
}


public class Main {
    static int n;
    static Job[] job;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        dp = new int[n];
        job = new Job[n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            job[i] = new Job(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())                          
            );
            dp[i] = Integer.MIN_VALUE;
        }

        Arrays.sort(job);


        dp[0] = job[0].p;
        for(int i=1; i<n; i++){
            for(int j=0; j<i; j++){
                if (inRange(job[i], job[j])){
                    dp[i] = Math.max(dp[i], job[j].p + dp[i]);
                }
            }

            dp[i] = Math.max(dp[i], job[i].p);
        }

        int max = Integer.MIN_VALUE;
        for(int n : dp) max = Math.max(max, n);
        System.out.println(max);
    }

    public static boolean inRange(Job now, Job previous){
        if (now.s <= previous.e) return false;
        return true;
    }
}