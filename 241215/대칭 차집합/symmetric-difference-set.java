/*
전체에서 교집합을 빼면?
*/

import java.io.*;
import java.util.*;
import java.util.HashSet;

public class Main {
    static int n, m;
    static int[] a, b;
    static HashSet<Integer> allSet = new HashSet<>();
    static HashSet<Integer> sameSet = new HashSet<>();    
    static HashSet<Integer> aSet = new HashSet<>();
    static HashSet<Integer> bSet = new HashSet<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        a = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            a[i] = Integer.parseInt(st.nextToken());
            allSet.add(a[i]);
            aSet.add(a[i]);
        }

        b = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++){
            b[i] = Integer.parseInt(st.nextToken());
            allSet.add(b[i]);
            bSet.add(b[i]);
        }

        if (n > m){
            for(int i=0; i<n; i++){
                if (bSet.contains(a[i])){
                    sameSet.add(a[i]);
                }   
            }
        } else {
            for(int i=0; i<m; i++){
                if (aSet.contains(b[i])){
                    sameSet.add(b[i]);
                }   
            }            
        }

        int result = allSet.size() - sameSet.size();
        System.out.println(result);
    }
}