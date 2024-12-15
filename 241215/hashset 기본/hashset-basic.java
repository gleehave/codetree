import java.io.*;
import java.util.StringTokenizer;
import java.util.HashSet;

public class Main {
    static int n;
    static HashSet<Integer> hashSet = new HashSet<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int value = Integer.parseInt(st.nextToken());
            switch(command){
                case "find":
                    if (hashSet.contains(value)){
                        System.out.println("true");
                    } else {
                        System.out.println("false");
                    }
                    break;
                case "add":
                    hashSet.add(value);                
                    break;
                case "remove":
                    hashSet.remove(value);                                    
                    break;                    
            }
        }
    }
}