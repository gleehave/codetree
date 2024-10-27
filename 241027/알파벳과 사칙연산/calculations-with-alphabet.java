/*
abcdef는 1~4의 값을 갖는다.
우선순위는 모두 동일하다.

앞에 -이면 무조건 1이다.
+, * 이면 무조건 4이다.
------

*/

import java.util.*;
import java.io.*;

public class Main {
    static String operation;
    static int[] alphabet;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        operation = br.readLine();

        alphabet = new int[6]; // a b c d e f

        for(int i=0; i<operation.length(); i++){
            if (operation.charAt(i) == '-'){
                alphabet[operation.charAt(i+1)-'a'] = 1;
            }
        }

        for(int i=0; i<6; i++){
            if (alphabet[i] == 1) continue;
            alphabet[i] = 4;
        }

        // 시작점
        int result = alphabet[operation.charAt(0)-'a'];
        for(int i=1; i<operation.length()-1; i++){
            if (operation.charAt(i) == '-'){
                result -= alphabet[operation.charAt(i+1) -'a'];
            } else if (operation.charAt(i) == '+'){
                result += alphabet[operation.charAt(i+1) - 'a'];
            } else if (operation.charAt(i) == '*'){
                result *= alphabet[operation.charAt(i+1) - 'a'];
            }
        }
        System.out.println(result);
    }
}