/*
1. 문자열에 대해서 n번 오른쪽 shift
2. 압축
    i != i+1 이면 a3입력하고, 0으로 초기화
*/
import java.io.*;
import java.util.*;

public class Main {

    static int min;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] data = br.readLine().toCharArray();

        int min = Integer.MAX_VALUE;
        int shiftTime = 1;
        while(shiftTime <= data.length){
            
            char[] shiftArr = rightShift(data);
            min = Math.min(compress(shiftArr).length(), min);
            shiftTime++;
        }
        System.out.println(min);
    }

    public static char[] rightShift(char[] data){
        char[] shiftArr = Arrays.copyOfRange(data, 0, data.length);
        char temp = data[data.length-1];
        for(int i=data.length-1; i>0; i--){
            shiftArr[i] = shiftArr[i-1];
        }
        shiftArr[0] = temp;

        return shiftArr;
    }

    public static String compress(char[] shiftArr){
        StringBuilder builder = new StringBuilder();
        int[] alphabet = new int['z'-'a'+1];

        for(int i=0; i<shiftArr.length-1; i++){
            alphabet[shiftArr[i] - 'a']++;
            if (shiftArr[i] != shiftArr[i+1]){
                builder.append(shiftArr[i]).append(alphabet[shiftArr[i]-'a']);
                Arrays.fill(alphabet, 0);
            }
        }

        return builder.toString();
    }
}