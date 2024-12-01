/*
동등한 상황은 무엇일까?
- 지금까지 선택한 학생
- 지금까지 능력 합
-> 다음 학생을 뽑을 때에 동등한 상황이다.
*/

import java.util.*;
import java.io.*;

class Student{
    int football;
    int baseball;

    public Student(int football, int baseball){
        this.football = football;
        this.baseball = baseball;
    }
}

public class Main {
    static int n;
    static Student[] people;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        people = new Student[n+1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            people[i] = new Student(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
            );
        }

        dp = new int[n+1];
        for(int i=1; i<=n; i++){
            dp[i] = Integer.MIN_VALUE;
        }

        for(int i=1; i<=n; i++){
            dp[i] = Math.max(dp[i-1] + people[i].baseball, dp[i-1] + people[i].football);
        }
        System.out.println(dp[n]);
    }
}