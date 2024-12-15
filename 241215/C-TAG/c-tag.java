/*
M이 최대 50개면, 50개 중에 3개를 뽑는다. 
선택된 3개의 열을 대상으로 집합 A, 집합 B에 대해서 넣어보자.
만약 서로 같은게 없으면 조합+1, 그렇지 않으면 Fail
*/

import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static String[][] map;
    static HashSet<String> aSet = new HashSet<>();
    static HashSet<String> bSet = new HashSet<>();
    
    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new String[2 * n][m];
        for(int i=0; i<2*n; i++){
            String[] data = br.readLine().split("");
            for(int j=0; j<m; j++){
                map[i][j] = data[j];
            }
        }

        int count = 0;
        for(int i=0; i<m; i++){
            for(int j=i+1; j<m; j++){
                for(int k=j+1; k<m; k++){
                    
                    afind(i, j, k);
                    bfind(i, j, k);

                    boolean available = true;
                    for(String aWord : aSet){
                        if (bSet.contains(aWord)){
                            available = false;
                            break;
                        }
                    }
                    if (available) count++;
                }
            }
        }
        System.out.println(count);

    } // main

    public static void afind(int i, int j, int k){
        aSet = new HashSet<>();
        for(int r=0; r<n; r++){
            aSet.add(map[r][i]+map[r][j]+map[r][k]);
        }
    }

    public static void bfind(int i, int j, int k){
        bSet = new HashSet<>();
        for(int r=n; r<2 * n; r++){
            bSet.add(map[r][i]+map[r][j]+map[r][k]);
        }
    }
}