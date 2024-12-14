import java.io.*;
import java.util.*;

public class Main {
    static String word;  
    static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        word = br.readLine();

        for(int i=0; i<word.length(); i++){
            map.put(
                word.charAt(i), 
                map.getOrDefault(word.charAt(i), 0) + 1
            );
        }

        int count = 0;
        for(char key : map.keySet()){
            if (map.get(key) == 1) count++;
        }

        if (count == 0) System.out.println("None");
        else if (count >= 1){
            for(char key : map.keySet()){
                if (map.get(key) == 1){
                    System.out.println(key);
                    break;
                } 
            }   
        } 
    }
}