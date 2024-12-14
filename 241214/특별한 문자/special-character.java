import java.io.*;
import java.util.*;

public class Main {
    static String word;
    static int[] alphabet = new int[26];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        word = br.readLine();

        for(int i=0; i<word.length(); i++){
            alphabet[word.charAt(i) - 'a']++;
        }
        
        List<Integer> one = new ArrayList<>();
        for(int i=0; i<alphabet.length; i++){
            if (alphabet[i] == 1) one.add(i);
        }

        if (one.size() == 1){
            char res = (char) ('a' + one.get(0));
            System.out.println(res);
        } else if (one.size() == 0){
            System.out.println("None");
        } else {
            Collections.sort(one);
            char res = (char) ('a' + one.get(0));
            System.out.println(res);
        }

    }
}